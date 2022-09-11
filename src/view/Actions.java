package view;

/**
 * This enum contains actions for EasyAnimator.
 * @author whitneycai
 *
 */
public enum Actions {
  START, STOP, RESTART, SLOWER, FASTER, LOOP;
  
  @Override
  public String toString() {
    return this.name();
  }

}
