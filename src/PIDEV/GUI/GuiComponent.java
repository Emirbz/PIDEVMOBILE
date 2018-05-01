package PIDEV.GUI;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;


public class GuiComponent extends Form  {
    public GuiComponent(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Layered_Layout = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    private com.codename1.components.ImageViewer gui_ImageProfil = new com.codename1.components.ImageViewer();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("GuiComponent");
        setName("GuiComponent");
        addComponent(gui_Layered_Layout);
        gui_Layered_Layout.setPreferredSizeStr("inherit 32.27513mm");
        gui_Layered_Layout.setUIID("ContainerHeader");
                gui_Layered_Layout.setInlineStylesTheme(resourceObjectInstance);
        gui_Layered_Layout.setInlineAllStyles("bgImage:welcome-background.jpg;");
        gui_Layered_Layout.setName("Layered_Layout");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Layered_Layout.getLayout()).setPreferredWidthMM((float)91.269844);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Layered_Layout.getLayout()).setPreferredHeightMM((float)32.27513);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Layered_Layout.getParent().getLayout()).setInsets(gui_Layered_Layout, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Layered_Layout, "-1 -1 -1 -1").setReferencePositions(gui_Layered_Layout, "0.0 0.0 0.0 0.0");
        gui_Layered_Layout.addComponent(gui_ImageProfil);
        gui_ImageProfil.setPreferredSizeStr("22.751324mm 20.37037mm");
        gui_ImageProfil.setUIID("PhotoDeProfil");
                gui_ImageProfil.setInlineStylesTheme(resourceObjectInstance);
        gui_ImageProfil.setInlineAllStyles("bgImage:;");
        gui_ImageProfil.setName("ImageProfil");
        ((com.codename1.ui.layouts.LayeredLayout)gui_ImageProfil.getParent().getLayout()).setInsets(gui_ImageProfil, "2.3809524mm auto 6.3492064mm 2.1164036mm").setReferenceComponents(gui_ImageProfil, "-1 -1 -1 -1").setReferencePositions(gui_ImageProfil, "0.0 0.0 0.0 0.0");
        gui_Layered_Layout.setPreferredSizeStr("inherit 32.27513mm");
        gui_Layered_Layout.setUIID("ContainerHeader");
                gui_Layered_Layout.setInlineStylesTheme(resourceObjectInstance);
        gui_Layered_Layout.setInlineAllStyles("bgImage:welcome-background.jpg;");
        gui_Layered_Layout.setName("Layered_Layout");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Layered_Layout.getLayout()).setPreferredWidthMM((float)91.269844);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Layered_Layout.getLayout()).setPreferredHeightMM((float)32.27513);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Layered_Layout.getParent().getLayout()).setInsets(gui_Layered_Layout, "0.0mm 0.0mm auto 0.0mm").setReferenceComponents(gui_Layered_Layout, "-1 -1 -1 -1").setReferencePositions(gui_Layered_Layout, "0.0 0.0 0.0 0.0");
    }// </editor-fold>
//-- DON'T EDIT ABOVE THIS LINE!!!
}
