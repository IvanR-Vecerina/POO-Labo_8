package engine;

public enum TeamColour {
    WHITE {
        final int pawnDirection = 1;
    },
    BLACK {
        final int pawnDirection = -1;
    }
}
