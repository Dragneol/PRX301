/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.ultil;

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
 * @author Decen
 */
public class XJCGeneratorObject {
    public static void main(String[] args) {
//        generate("thang.genobj.webcrawlinfo","web/WEB-INF/xsd/webConfig.xsd");
//        generate("thang.genobj","web/WEB-INF/xsd/categoryLink.xsd");
//        generate("thang.genobj","web/WEB-INF/xsd/products.xsd");
//        generate("thang.genobj","web/WEB-INF/xsd/user.xsd");
    }
    public static void generate(String packageStr, String xsdUrl) {
        try {
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {
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

            sc.forcePackageName(packageStr);
            File schema = new File(xsdUrl);
            InputSource is = new InputSource(schema.toURI().toString());
            sc.parseSchema(is);
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File("src/java"));
            System.out.println("Finished");
        } catch (IOException ex) {
            Logger.getLogger(XJCGeneratorObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
