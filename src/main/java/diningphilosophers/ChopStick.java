package diningphilosophers;

public class ChopStick {

    // Le nombre total de baguettes
    private static int stickCount = 0;
    // Le numéro de chaque baguette
    private final int myNumber;
    // Est-ce que ma baguette est libre ?
    private boolean iAmFree = true;

    public ChopStick() {
        // Chaque baguette est numérotée 
        myNumber = ++stickCount;
    }

    
    synchronized void take() throws InterruptedException {
        // Attendre que la baguette soit libre
        while (!iAmFree) {
            wait(); // Peut lever InterruptedException
        }
        assert (iAmFree);
        this.iAmFree = false;
        System.out.printf("La baguette " + myNumber + " est prise ");
        notifyAll(); // Notifier que la baguette est prise
    }

    synchronized void release() throws InterruptedException {
        // Attendre que la pile soit prise
        while (iAmFree) {
            wait(); // Peut lever InterruptedException
        }
        assert (!iAmFree);
        this.iAmFree = true;
        System.out.printf("La baguette " + myNumber + " est libre ");
        notifyAll(); // Notifier que la baguette est libre
    }

    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
