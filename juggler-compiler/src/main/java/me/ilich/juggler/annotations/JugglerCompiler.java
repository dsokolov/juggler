package me.ilich.juggler.annotations;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

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
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        CodeGenerator codeGenerator = new CodeGenerator(processingEnv);
        for (Element e : roundEnv.getElementsAnnotatedWith(JugglerFragment.class)) {
            generateClass(codeGenerator, e);
        }
        for (Element e : roundEnv.getElementsAnnotatedWith(JugglerToolbarFragment.class)) {
            generateClass(codeGenerator, e);
        }
        return true;
    }

    private void generateClass(CodeGenerator codeGenerator, Element e) {
        JavaFile javaFile = codeGenerator.generateFragment(e);
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
