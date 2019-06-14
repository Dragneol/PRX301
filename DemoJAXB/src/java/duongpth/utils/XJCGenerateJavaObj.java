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
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author dragn
 */
public class XJCGenerateJavaObj implements Serializable {

    public static void main(String[] args) {
        try {
            String output = "src/";
            SchemaCompiler compiler = XJC.createSchemaCompiler();
            compiler.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println(saxpe.getMessage());
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println(saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println(saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println(saxpe.getMessage());
                }
            });
            compiler.forcePackageName("duongpth.jaxb.app");
            File schema = new File("customer.xsd");
            InputSource is = new InputSource(schema.toURI().toString());
            compiler.parseSchema(is);
            S2JJAXBModel model = compiler.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(output));
            System.out.println("Finished");
        } catch (IOException ex) {
            Logger.getLogger(XJCGenerateJavaObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
