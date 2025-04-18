package myplugin;

public class MyPluginMojo extends AbstractMojo{
    public void execute() throws MojoExecutionException, MojoFailureException{
        System.out.println("Hello World");
    }
}
