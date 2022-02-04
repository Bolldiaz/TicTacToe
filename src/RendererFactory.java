/**
 * This factory creates instances of Renderer implemented classes.
 * @author Matan Dizitser
 */
public class RendererFactory {

    /**
     * The types name, for the user input check
     */
    public static final String RENDERERS_TYPES = "{console, none}";

    /**
     * The type name of the ConsoleRenderer
     */
    public static final String CONSOLE = "console";

    /**
     * The type name of the VoidRenderer
     */
    public static final String VOID = "none";

    /**
     * Create a renderer instance - Console/Void, according the renderer-type
     * @param rendererType none for VoidRenderer, console for ConsoleRenderer
     * @return a Console/VoidRenderer instance
     */
    Renderer buildRenderer(String rendererType) {
        switch (rendererType) {
            case CONSOLE:
                return new ConsoleRenderer();
            case VOID:
                return new VoidRenderer();
            default:
                return null;
        }
    }
}
