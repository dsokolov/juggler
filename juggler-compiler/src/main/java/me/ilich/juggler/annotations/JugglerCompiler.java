package me.ilich.juggler.annotations;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import static com.squareup.javapoet.JavaFile.builder;
import static javax.lang.model.SourceVersion.latestSupported;

@AutoService(Processor.class)
public class JugglerCompiler extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(JugglerFragment.class.getCanonicalName());
        set.add(JugglerToolbarFragment.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return latestSupported();
    }

    @Override
    //TODO переписать, выделить абстракции, сделать проверку класса
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getElementsAnnotatedWith(JugglerFragment.class)) {
            TypeElement annotatedClass = (TypeElement) e;
            String packageName = processingEnv.getElementUtils().getPackageOf(e).getQualifiedName().toString();
            TypeSpec generatedClass = TypeSpec.classBuilder("_Juggler_" + annotatedClass.getSimpleName().toString()).
                    addJavadoc("Juggler generated class").
                    addModifiers(Modifier.PUBLIC, Modifier.FINAL).
                    superclass(TypeName.get(annotatedClass.asType())).
                    addMethod(fragmentOnCreate()).
                    addMethod(fragmentOnStart()).
                    build();
            JavaFile javaFile = builder(packageName, generatedClass).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        for (Element e : roundEnv.getElementsAnnotatedWith(JugglerToolbarFragment.class)) {
            JugglerToolbarFragment annotation = e.getAnnotation(JugglerToolbarFragment.class);
            int toolbarLayoutId = annotation.value();
            TypeElement annotatedClass = (TypeElement) e;
            String packageName = processingEnv.getElementUtils().getPackageOf(e).getQualifiedName().toString();
            String className = Tools.getJugglerClassName(annotatedClass.getSimpleName().toString());
            TypeSpec generatedClass = TypeSpec.classBuilder(className).
                    addJavadoc("Juggler generated class").
                    addModifiers(Modifier.PUBLIC).
                    superclass(TypeName.get(annotatedClass.asType())).
                    addMethod(fragmentOnCreate()).
                    addMethod(fragmentOnViewCreated(toolbarLayoutId)).
                    addMethod(fragmentOnStart()).
                    build();
            JavaFile javaFile = JavaFile.builder(packageName, generatedClass).
                    addFileComment("Juggler").
                    build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return true;
    }

    private MethodSpec fragmentOnCreate() {
        return MethodSpec.
                methodBuilder("onCreate").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addParameter(TypeVariableName.get("android.os.Bundle"), "savedInstanceState").
                addCode("\tsuper.onCreate(savedInstanceState);\n").
                addCode("\tme.ilich.juggler.staticjuggler.History.INSTANCE.onFragmentCreated(this);\n").
                build();
    }

    private MethodSpec fragmentOnStart() {
        return MethodSpec.
                methodBuilder("onStart").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addCode("super.onStart();\n").
                addCode("\tme.ilich.juggler.staticjuggler.History.INSTANCE.onFragmentStarted(this);\n").
                build();
    }

    private MethodSpec fragmentOnViewCreated(int toolbarId) {
        return MethodSpec.
                methodBuilder("onViewCreated").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addParameter(TypeVariableName.get("android.view.View"), "view").
                addParameter(TypeVariableName.get("android.os.Bundle"), "savedInstanceState").
                addCode("\tsuper.onViewCreated(view, savedInstanceState);\n").
                addCode("\tandroid.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) view.findViewById($L);\n", toolbarId).
                addCode("\t((android.support.v7.app.AppCompatActivity) getActivity()).setSupportActionBar(toolbar);\n").
                addCode("\tme.ilich.juggler.staticjuggler.History.INSTANCE.onFragmentToolbar(this, toolbar);\n").
                build();
    }

}
