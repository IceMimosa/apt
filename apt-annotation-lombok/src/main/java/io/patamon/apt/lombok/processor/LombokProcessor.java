package io.patamon.apt.lombok.processor;

import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Desc: lombok的注解处理类
 * <p>
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2018/2/4
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes({"io.patamon.apt.lombok.annotation.*"})
public class LombokProcessor extends AbstractProcessor {

    private Trees trees;
    private TreeMaker treeMaker;
    private Names names;

    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.trees = Trees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                if (element.getKind().isClass()) {
                    JCTree tree = (JCTree) trees.getTree(element);
                     tree.accept(new LombokTreeTranslator(treeMaker, names, messager));

//                    JCTree.JCClassDecl classDecl = ((JCTree.JCClassDecl) tree);
//                    for (JCTree var : ((JCTree.JCClassDecl) tree).defs) {
//                        if (var instanceof JCTree.JCVariableDecl) {
//                            JCTree.JCMethodDecl methodGetter = this.createGetterMethod((JCTree.JCVariableDecl) var);
//                            classDecl.defs = classDecl.defs.append(methodGetter);
//
//                        }
//                    }
                }
            }
        }
        return false;
    }

    private JCTree.JCMethodDecl createGetterMethod(JCTree.JCVariableDecl jcVariableDecl) {
        JCTree.JCMethodDecl jcMethodDecl = treeMaker.MethodDef(
                treeMaker.Modifiers(Flags.PUBLIC),
                names.fromString("getName"),
                (JCTree.JCExpression) jcVariableDecl.getType(),
                List.nil(),
                List.nil(),
                List.nil(),
                treeMaker.Block(0L, List.of(treeMaker.Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")), names.fromString("name"))))),
                null
        );
        // jcMethodDecl.sym = new Symbol.MethodSymbol(Flags.PUBLIC, names.fromString("getName"), jcVariableDecl.getType().type, jcClassDecl.sym);
        return jcMethodDecl;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
