package io.patamon.apt.lombok.processor;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;
import io.patamon.apt.lombok.annotation.Getter;

import javax.annotation.processing.Messager;

import static com.sun.tools.javac.tree.JCTree.JCAnnotation;
import static com.sun.tools.javac.tree.JCTree.JCModifiers;
import static com.sun.tools.javac.tree.JCTree.JCVariableDecl;

/**
 * Desc:
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2018/2/4
 */
public class LombokTreeTranslator extends TreeTranslator {

    private final Messager messager;
    private TreeMaker treeMaker;
    private Names names;

    private JCClassDecl jcClassDecl;

    private JCMethodDecl getter;

    public LombokTreeTranslator(TreeMaker treeMaker, Names names, Messager messager) {
        this.treeMaker = treeMaker;
        this.names = names;
        this.messager = messager;
    }

    @Override
    public void visitClassDef(JCClassDecl jcClassDecl) {
        this.jcClassDecl = jcClassDecl;
        super.visitClassDef(jcClassDecl);
        if (getter != null) {
            jcClassDecl.defs = jcClassDecl.defs.append(getter);
        }
        this.result = this.jcClassDecl;
    }

    @Override
    public void visitVarDef(JCVariableDecl jcVariableDecl) {
        super.visitVarDef(jcVariableDecl);
        JCModifiers modifiers = jcVariableDecl.getModifiers();
        List<JCAnnotation> annotations = modifiers.getAnnotations();
        if (annotations == null || annotations.size() <= 0) {
            return;
        }
        for (JCAnnotation annotation : annotations) {
            if (Getter.class.getName().equals(annotation.type.toString())) {
                // 生成getter方法
                JCMethodDecl getterMethod = createGetterMethod(jcVariableDecl);
                getter = getterMethod;
            }
        }
    }

    private JCMethodDecl createGetterMethod(JCVariableDecl jcVariableDecl) {
        JCMethodDecl jcMethodDecl = treeMaker.MethodDef(
                treeMaker.Modifiers(Flags.PUBLIC),
                names.fromString("getName"),
                (JCExpression) jcVariableDecl.getType(),
                List.nil(),
                List.nil(),
                List.nil(),
                treeMaker.Block(0L, List.of(treeMaker.Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")), names.fromString("name"))))),
                null
        );
        // jcMethodDecl.sym = new Symbol.MethodSymbol(Flags.PUBLIC, names.fromString("getName"), jcVariableDecl.getType().type, jcClassDecl.sym);
        return jcMethodDecl;
    }
}
