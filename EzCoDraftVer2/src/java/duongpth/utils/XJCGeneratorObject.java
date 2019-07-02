/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author dragn
 */
public class XJCGeneratorObject {

    public static void main(String[] args) {
//        generate("duongpth.jaxbs", "web/WEB-INF/xsd/ingredients.xsd");
//        generate("duongpth.jaxbs", "web/WEB-INF/xsd/recipes.xsd");
    }

    private static void generate(String packageStr, String xsdUri) {

        SchemaCompiler sc = XJC.createSchemaCompiler();
        sc.setErrorListener(new ErrorListener() {
            @Override
            public void error(SAXParseException saxpe) {
                System.out.println("ERROR at XJC Generator: " + saxpe.getMessage());
            }

            @Override
            public void fatalError(SAXParseException saxpe) {
                System.out.println("ERROR at XJC Generator: " + saxpe.getMessage());
            }

            @Override
            public void warning(SAXParseException saxpe) {
                System.out.println("ERROR at XJC Generator: " + saxpe.getMessage());
            }

            @Override
            public void info(SAXParseException saxpe) {
                System.out.println("ERROR at XJC Generator: " + saxpe.getMessage());
            }
        });
        sc.forcePackageName(packageStr);
        File schema = new File(xsdUri);
        InputSource inputSource = new InputSource(schema.toURI().toString());
        sc.parseSchema(inputSource);
        S2JJAXBModel model = sc.bind();
        JCodeModel code = model.generateCode(null, null);
        try {
            code.build(new File("src/java"));
        } catch (IOException ex) {
            Logger.getLogger(XJCGeneratorObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Generarion Finished");

    }
}
