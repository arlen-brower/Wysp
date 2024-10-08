package capstone.prototype.problem;

import java.util.*;

import capstone.prototype.game.Actions;
import capstone.prototype.game.GameState;
import capstone.prototype.problem.states.CornersProblemState;
import capstone.prototype.problem.states.ProblemState;
import capstone.prototype.types.Direction;
import capstone.prototype.types.Grid;
import capstone.prototype.types.Position;
import capstone.prototype.types.Triple;
import capstone.prototype.types.Tuple;
import capstone.prototype.types.Vector;

// TODO: make java version of the comments
// TODO: check against completed python implementation
public class CornersProblem extends SearchProblem {
    /*
     * This search problem finds paths through all four corners of a layout.
     * 
     * You must select a suitable state space and successor function
     */

    public Grid walls;
    public Position startingPosition;
    public List<Position> corners;
    public int _expanded;
    public List<Integer> initialState;

    public CornersProblem(GameState startingGameState) {
        /* Stores the walls, pacman's starting position and corners. */
        this.walls = startingGameState.getWalls();
        this.startingPosition = startingGameState.getPacmanPosition();

        int top = this.walls.height - 2;
        int right = this.walls.width - 2;

        this.corners = Arrays.asList(new Position(1, 1), new Position(1, top), new Position(right, 1),
                new Position(right, top));

        for (Position corner : this.corners) {
            if (!startingGameState.hasFood(corner))
                System.out.println("[java] Warning: no food in corner " + corner.stringCoords());
        }

        this._expanded = 0; // WARNING: do NOT change this value manually

        /*
         * Please add any code below here which you would like to use in initializing the
         * problem
         */
        //Your Code Here
    }

    @Override
    public ProblemState getStartState() {
        /*
         * Returns the start state (in your state space, not the full Pacman state
         * space)
         */
        //Your Code Here *can remove thrown exception once finished*
        throw new UnsupportedOperationException(
                "Unimplemented method 'getStartState() in CornersProblem'");
    }

    @Override
    public boolean isGoalState(ProblemState inState) {
        /* Returns whether this search state is a goal state of the problem. */
        //Your Code Here
        throw new UnsupportedOperationException(
            "Unimplemented method 'isGoalState() in CornersProblem'");
    }

    @Override
    public List<Triple<ProblemState, Direction, Integer>> getSuccessors(ProblemState problemState) {
        /*
         * Returns successor states, the actions they require, and a cost of 1.
         * 
         * As noted in search.py of the original project:
         * * For a given state, this should return a list of triples, (successor,
         * * action, stepCost), where 'successor' is a successor to the current
         * * state, 'action' is the action required to get there, and 'stepCost'
         * * is the incremental cost of expanding to that successor
         */
        CornersProblemState state = (CornersProblemState) problemState;
        Position pos = state.state.first;
        List<Integer> corn = state.state.second;

        List<Triple<ProblemState, Direction, Integer>> successors = new ArrayList<>();

        for (Direction action : Direction.values()) {
            /*  Add a successor state to the successor list if the action is legal
                Here's a code snippet for figuring out whether a new position hits a wall:

                if (action == Direction.STOP)
                    continue;

                double x = pos.x;
                double y = pos.y;
                Vector v = Actions.directionToVector(action);
                int nextX = (int) (x + v.x);
                int nextY = (int) (y + v.y);
                Position nextPos = new Position((double) nextX, (double) nextY);

                // prevent searching in the negatives/over the walls
                if (nextX < 0 || nextY < 0 || nextX >= this.walls.width || nextY >= this.walls.height)
                    continue;
            */
        }

        this._expanded++; //DO NOT CHANGE
        return successors;
    }

    @Override
    public int getCostOfActions(List<Direction> actions) {
        //Returns the cost of a particular sequence of actions.  If those actions
        //include an illegal move, return 999999.  This is implemented for you.

        if (actions == null || actions.isEmpty())
            return 999999;

        double x = this.startingPosition.x;
        double y = this.startingPosition.y;

        for (Direction action : actions) {
            Vector v = Actions.directionToVector(action);

            int x_i = (int) (x + v.x);
            int y_i = (int) (y + v.y);

            if (this.walls.data.get(x_i).get(y_i))
                return 999999;
        }
        return actions.size();
    }
}