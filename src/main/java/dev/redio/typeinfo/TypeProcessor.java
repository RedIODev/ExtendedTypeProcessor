package dev.redio.typeinfo;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;

import dev.redio.typeinfo.annotations.Retain;

@SupportedAnnotationTypes("dev.redio.typeinfo.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TypeProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement typeElement : annotations) {
            var annotatedElements = roundEnv.getElementsAnnotatedWith(typeElement);
            if (typeElement.getQualifiedName().contentEquals(Retain.class.getTypeName()))
                for (var element : annotatedElements) {
                    processRetain(element, roundEnv);
                }
        }
        return true;
    }

    private void processRetain(Element element, RoundEnvironment roundEnv) {
        switch (element.getKind()) {
            case TYPE_PARAMETER -> processRetainSingleParam(element, roundEnv);
            case CLASS -> processRetainClass(element, roundEnv);
            case METHOD -> processRetainMethod(element, roundEnv);
            default -> processingEnv.getMessager().printError("Retain is not valid for this element", element);
        }
    }

    private void processRetainSingleParam(Element element, RoundEnvironment roundEnv) {
        var m = processingEnv.getMessager();
        m.printNote("Parameter: " + element);
        if (element instanceof TypeParameterElement e)
            m.printNote("Is type parameter!");
        m.printNote("From:" + element.getEnclosingElement());
    }

    private void processRetainClass(Element element, RoundEnvironment roundEnv) {
        var m = processingEnv.getMessager();
        m.printNote("Class: " + element);
        if (element instanceof TypeElement e)
            m.printNote("Is type!");
    }

    private void processRetainMethod(Element element, RoundEnvironment roundEnv) {
        var m = processingEnv.getMessager();
        m.printNote("Method: " + element);
        if (element instanceof ExecutableElement)
            m.printNote("Is executable!");
    }
}
