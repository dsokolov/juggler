package me.ilich.juggler.annotations;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

class CodeGenerator {

    private final ProcessingEnvironment processingEnv;

    CodeGenerator(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    JavaFile generateFragment(Element e) {
        if (e == null) {
            throw new NullPointerException("element");
        }
        if (!(e instanceof TypeElement)) {
            throw new IllegalArgumentException("element");
        }
        TypeElement annotatedClass = (TypeElement) e;
        String packageName = processingEnv.getElementUtils().getPackageOf(e).getQualifiedName().toString();
        String className = Tools.getJugglerClassName(annotatedClass.getSimpleName().toString());
        TypeSpec generatedClass = TypeSpec.classBuilder(className).
                addJavadoc("Juggler autogenerated class").
                addModifiers(Modifier.PUBLIC, Modifier.FINAL).
                superclass(TypeName.get(annotatedClass.asType())).
                addMethod(fragmentOnCreate()).
                addMethod(fragmentOnStart()).
                addMethod(fragmentOnResume()).
                addMethod(fragmentOnPause()).
                addMethod(fragmentOnStop()).
                addMethod(fragmentOnSaveInstanceState()).
                addMethod(fragmentOnViewCreated()).
                build();
        return JavaFile.builder(packageName, generatedClass).build();
    }

    private MethodSpec fragmentOnCreate() {
        return MethodSpec.
                methodBuilder("onCreate").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addParameter(TypeVariableName.get("android.os.Bundle"), "savedInstanceState").
                addCode("\tsuper.onCreate(savedInstanceState);\n").
                addCode("\tme.ilich.juggler.staticjuggler.Dispatcher.INSTANCE.onFragmentCreated(this, savedInstanceState);\n").
                build();
    }

    private MethodSpec fragmentOnStart() {
        return MethodSpec.
                methodBuilder("onStart").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addCode("\tsuper.onStart();\n").
                addCode("\tme.ilich.juggler.staticjuggler.Dispatcher.INSTANCE.onFragmentStarted(this);\n").
                build();
    }

    private MethodSpec fragmentOnResume() {
        return MethodSpec.
                methodBuilder("onResume").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addCode("super.onResume();\n").
                addCode("\tme.ilich.juggler.staticjuggler.Dispatcher.INSTANCE.onFragmentResumed(this);\n").
                build();
    }

    private MethodSpec fragmentOnPause() {
        return MethodSpec.
                methodBuilder("onPause").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addCode("super.onPause();\n").
                addCode("\tme.ilich.juggler.staticjuggler.Dispatcher.INSTANCE.onFragmentPaused(this);\n").
                build();
    }

    private MethodSpec fragmentOnStop() {
        return MethodSpec.
                methodBuilder("onStop").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addCode("super.onStop();\n").
                addCode("\tme.ilich.juggler.staticjuggler.Dispatcher.INSTANCE.onFragmentStopped(this);\n").
                build();
    }

    private MethodSpec fragmentOnDestroy() {
        return MethodSpec.
                methodBuilder("onDestroy").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addCode("super.onDestroy();\n").
                addCode("\tme.ilich.juggler.staticjuggler.Dispatcher.INSTANCE.onFragmentDestroy(this);\n").
                build();
    }

    private MethodSpec fragmentOnViewCreated() {
        return MethodSpec.
                methodBuilder("onViewCreated").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addParameter(TypeVariableName.get("android.view.View"), "view").
                addParameter(TypeVariableName.get("android.os.Bundle"), "savedInstanceState").
                addCode("\tsuper.onViewCreated(view, savedInstanceState);\n").
                addCode("\tme.ilich.juggler.staticjuggler.Dispatcher.INSTANCE.onFragmentViewCreated(this, view, savedInstanceState);\n").
                build();
    }

    private MethodSpec fragmentOnSaveInstanceState() {
        return MethodSpec.
                methodBuilder("onSaveInstanceState").
                addAnnotation(Override.class).
                addModifiers(Modifier.PUBLIC).
                addParameter(TypeVariableName.get("android.os.Bundle"), "savedInstanceState").
                addCode("super.onSaveInstanceState(savedInstanceState);\n").
                addCode("\tme.ilich.juggler.staticjuggler.Dispatcher.INSTANCE.onFragmentSaveInstanceState(this, savedInstanceState);\n").
                build();
    }

}
