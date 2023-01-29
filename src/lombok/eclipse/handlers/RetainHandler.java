package lombok.eclipse.handlers;

import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.internal.compiler.ast.Annotation;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeParameter;
import org.kohsuke.MetaInfServices;

import dev.redio.typeinfo.Retain;
import lombok.core.AnnotationValues;
import lombok.core.AST.Kind;
import lombok.eclipse.EclipseAnnotationHandler;
import lombok.eclipse.EclipseNode;
import lombok.libs.org.objectweb.asm.tree.AnnotationNode;

//http://notatube.blogspot.com/2010/12/project-lombok-creating-custom.html
@MetaInfServices(EclipseAnnotationHandler.class)
public class RetainHandler extends EclipseAnnotationHandler<Retain> {
    public static void main(String[] args) {

    }

    @Override
    public void handle(AnnotationValues<Retain> annotation, Annotation ast,
            EclipseNode annotationNode) {
        switch (annotationNode.getKind()) {
            case TYPE -> handleRetainType(annotationNode, ast);
            case METHOD -> {
            }
            case ARGUMENT -> {
            }
            default -> EclipseNode.CONSOLE.addError("Invalid kind for retain");
        }
        // EclipseNode node = annotationNode.up();
        // FieldDeclaration field =
    }

    private void handleRetainType(EclipseNode annotationNode, Annotation ast) {
        TypeDeclaration typeDecl = (TypeDeclaration) annotationNode.get();
        var params = typeDecl.typeParameters;
        for (TypeParameter typeParameter : params) {
            FieldDeclaration fd = new FieldDeclaration();
            fd.annotations = null;
            fd.modifiers = Modifier.PRIVATE;
            var name = typeParameter.name;
            var fieldName = new char[name.length + 2];
            fd.name = new char[]{'_', '_', ''};
            EclipseHandlerUtil.injectField(annotationNode, fd);
        }
        
    }
}
