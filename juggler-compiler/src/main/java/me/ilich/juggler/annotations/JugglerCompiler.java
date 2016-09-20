package me.ilich.juggler.annotations;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import static com.squareup.javapoet.JavaFile.builder;
import static java.util.Collections.singleton;
import static javax.lang.model.SourceVersion.latestSupported;

@AutoService(Processor.class)
public class JugglerCompiler extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return singleton(JugglerFragment.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getElementsAnnotatedWith(JugglerFragment.class)) {
            TypeElement annotatedClass = (TypeElement) e;
            String packageName = processingEnv.getElementUtils().getPackageOf(e).getQualifiedName().toString();
            TypeSpec generatedClass = TypeSpec.classBuilder("Juggler" + annotatedClass.getSimpleName().toString()).
                    addModifiers(Modifier.PUBLIC).
                    superclass(TypeName.get(annotatedClass.asType())).
                    addMethod(
                            MethodSpec.
                                    methodBuilder("onStart").
                                    addModifiers(Modifier.PUBLIC).
                                    addCode("super.onStart();\n").
                                    addCode("android.util.Log.v(\"Sokolov\",\"onStart1\");\n").
                                    build()
                    ).
                    build();
            JavaFile javaFile = builder(packageName, generatedClass).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return true;
    }

}
