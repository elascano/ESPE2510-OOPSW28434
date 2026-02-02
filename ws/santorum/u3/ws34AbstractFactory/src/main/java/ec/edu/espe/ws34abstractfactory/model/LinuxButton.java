package ec.edu.espe.ws34abstractfactory.model;

/**
 *
 * @author Thais Santorum
 */
class LinuxButton extends Button {
  public void paint() {
      System.out.println("Im a Linux Button: " + caption);
  }  
}
