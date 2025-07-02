# QuarrelShips Code Analysis: Code Smells and Design Patterns

## Overview

This document analyzes the QuarrelShips (Battleships) codebase for code smells, design patterns, and improvement opportunities. The analysis focuses on identifying problematic areas and suggesting concrete improvements.

---

## 🔍 **Code Smells Identified**

### 1. **God Class / Large Class Smell** - `Board.java`

**Location:** `src/components/board/Board.java`

**Problem:** The Board class has too many responsibilities:

- Managing cell grid (10x10)
- Ship placement logic
- Ship collision detection
- Rendering/display logic
- Game state tracking
- Board conversion (setup → gameplay)

**Impact:**

- Violates Single Responsibility Principle
- Hard to test individual components
- High coupling between different concerns

**Before (Current Code):**

```java
public class Board {
    // Cell management
    private ArrayList<Cell> cells = new ArrayList<Cell>();
    private ArrayList<Cell> formattedCells = new ArrayList<Cell>();

    // Ship management
    private ArrayList<Ship> setShips = new ArrayList<Ship>();
    private ArrayList<Ship> unsetShips = new ArrayList<Ship>();
    private Ship activeShip;

    // Display logic
    private int r, g, b;

    // Game state
    private int amtShipCells = 0;

    // Mixed responsibilities in methods
    public void show(boolean showBoats, int r, int g, int b) {
        // Rendering logic mixed with game logic
    }

    public void convertToTurnBoard() {
        // Complex conversion logic
    }
}
```

**✅ FIXED:** This God Class smell has been resolved by extracting responsibilities into specialized classes:

- **BoardRenderer** (`BoardRenderer.java`): Handles all rendering and display logic
- **BoardLogic** (`BoardLogic.java`): Handles game logic, cell operations, and ship collision detection
- **ShipManager** (`ShipManager.java`): Manages ship placement and ship-related operations

**After (Refactored Code):**

```java
public class Board {
    // AFTER: Composition with specialized components
    private final BoardRenderer renderer;  // Handles rendering
    private final BoardLogic logic;        // Handles game logic
    private final ShipManager shipManager; // Manages ships

    // Simplified responsibilities - only manages cells and coordinates components
    private ArrayList<Cell> cells = new ArrayList<Cell>();
    private final ArrayList<Cell> formattedCells = new ArrayList<Cell>();
    private int amtShipCells = 0;

    public Board(QuarrelShips qs, int r, int g, int b) {
        // Initialize specialized components
        this.renderer = new BoardRenderer(qs, r, g, b);
        this.logic = new BoardLogic(qs);
        this.shipManager = new ShipManager(qs);
        // Initialize grid...
    }

    // Methods now delegate to appropriate components
    public void show(boolean showBoats, int r, int g, int b) {
        renderer.render(cells, shipManager.getSetShips(),
                       shipManager.getActiveShip(), showBoats, r, g, b, logic);
    }

    public void rotateActiveShip() {
        shipManager.rotateActiveShip(); // Delegate to ShipManager
    }

    public Cell getCellAt(int x, int y) {
        return logic.getCellAt(cells, x, y); // Delegate to BoardLogic
    }
}
```

**Benefits of the Fix:**

- ✅ **Single Responsibility Principle**: Each class now has one clear responsibility
- ✅ **Improved Testability**: Components can be tested independently
- ✅ **Reduced Coupling**: Logic, rendering, and ship management are separated
- ✅ **Enhanced Maintainability**: Changes to rendering won't affect game logic
- ✅ **Better Code Organization**: Related functionality is grouped together

---

## 🎯 **Design Patterns Identified**

### 1. **State Pattern** ✅

**Location:** `IGameMaster` interface and implementations

**Description:** The game uses different states (TitleScreen, Menu, Setup, Turn, GameOver) managed through the IGameMaster interface.

**Implementation:**

```java
public interface IGameMaster {
    void handleKeyPress(char key);
    void handleMouseClick();
    void handleMouseDrag();
    void show();
}

// Different states: TitleScreenMaster, MenuMaster, SetupMaster, TurnMaster, GameOverMaster
```

**Benefits:**

- Clean state transitions
- Encapsulated state-specific behavior
- Easy to add new game states

---

## ✅ **Implementation Status**

### ✅ **COMPLETED: Board God Class Refactoring**

**Status:** Successfully implemented

**Files Created:**

- `BoardRenderer.java` - Handles all rendering and display logic
- `BoardLogic.java` - Handles game logic, cell operations, and collision detection
- `ShipManager.java` - Manages ship placement and ship-related operations

**Files Modified:**

- `Board.java` - Refactored to use composition with specialized components

**Impact:**

- **Before:** Many lines of mixed responsibilities in one class
- **After:** Distributed across 4 focused classes with clear responsibilities
- **Maintainability:** Significantly improved - changes to rendering won't affect game logic
- **Testability:** Each component can now be tested independently
