package framework;

import graph.DequeAdder;
import graph.Vertex;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * This abstract class represents a problem in a problem solving domain.
 * Note that this class does not provide a constructor.
 * It provides getters and setters for the current state
 * of the problem, the list of moves for the problem, and the problem's
 * introduction string.
 * Extending classes need not have instance fields for these attributes, 
 * as they are inherited from this class.
 * Extending classes must set these values in their constructors using 
 * the setters (mutators) provided in this class.
 * Extending classes must also override the abstract <b>success</b> method.
 */
public abstract class Problem {

    public Problem() {
        
        this.deque = new LinkedList();
        this.headAdder = new DequeAdder() {
            @Override
            public void add(Vertex v, Deque<Vertex> d) {
                d.addFirst(v);
            }
        };
        this.tailAdder = new DequeAdder() {
            @Override
            public void add(Vertex v, Deque<Vertex> d) {
                d.addLast(v);
            }
        };
        this.stack = new Stack<Vertex>();
    }
    
    /**
     * Determines whether the current state of this problem is a success.
     * Extending classes need to override this method.
     * @return whether the current state is a success
     */
    public abstract boolean success();

    /**
     * Gets the current state of the problem.
     * @return the current state
     */
    public State getCurrentState() {
        return this.currentState;
    }
    
//    public State getInitialState() {
//        return this.initialState;
//    }

    /**
     * Sets the current state of the problem.
     * @param currentState the current state
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
    
//    public void setInitialState(State initialState) {
//        this.initialState = initialState;
//    }

    /**
     * Gets an explanatory introduction string for the problem.
     * @return the introduction string
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * Sets the introduction string for this problem.
     * @param introduction the introduction string
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * Gets the list of moves for this problem.
     * @return the list of moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Sets the list of moves for this problem.
     * @param moves the list of moves
     */
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    
    public Stack<Vertex> getStack() {
        return this.stack;
    }
    
    public int getQueueOps() {
        return this.queueOps;
    }
    
    public int getMaxQueueSize() {
        return this.maxQueueSize;
    }
    
    /**
     * Checks to see if a vertex appears on the predecessor path
     * of an ancestor vertex.
     * @param v the vertex to check
     * @param ancestor the ancestor of v
     * @return true if v equals ancestor or any ancestor of ancestor
     */
    public boolean occursOnPath(Vertex v, Vertex ancestor) {
        if (v.equals(ancestor)) {
            return true;
        }
        while (ancestor != null) {
            if (v.equals(ancestor)) {
                return true;
            }
            else {
                ancestor = ancestor.getPredecessor();
            }
        }
        return false;
    }

    /**
     * Expands a vertex v in a state space search tree by creating a list
     * (its children) of all vertices adjacent to it in the state space.
     * The list may not include any vertex on the predecessor path 
     * leading to v.
     * Each child on the list has its predecessor set to v and its distance
     * from the root (its depth in the search tree) set to one more than v's 
     * distance.
     * @param v the vertex being expanded
     * @return a list of the children
     */
    public List<Vertex> expand(Vertex v) {
        ArrayList<Vertex> children = new ArrayList<Vertex>();

        for (Move m: moves) {
            if (m.doMove((State) v) != null) {
                Vertex child = (Vertex) m.doMove((State) v);
            
                if (child != null && !occursOnPath(child, v)) {
                    child.setDistance(v.getDistance() + 1);
                    child.setPredecessor(v);
                    children.add(child);
                }
            }
        }
        this.queueOps = this.queueOps + children.size();
        this.queueSize = this.queueSize + children.size();
        if (this.queueSize > this.maxQueueSize) {
            this.maxQueueSize = this.queueSize;
        }
        return children;
    }
    
    public void solve(int search) {
        State state = this.currentState;
        Vertex start = (Vertex) this.currentState;
        Vertex end = null;
        
        if (search == 0) {
            end = search(start, this.tailAdder);
        }
        else {
            end = search(start, this.headAdder);
        }
        if (end == null) {
            JOptionPane.showMessageDialog(null, "Solution Not Found");
        }
        else {
            this.stack.clear();
            while (end != null) {
                this.stack.push(end);
                end = end.getPredecessor();
            }
        }
        this.currentState = state;
    }
    
    /**
     * The core search operation for this graph.
     * It uses a double-ended queue so that either breadth-first or
     * depth-first search can be performed depending on to which end of the
     * queue newly discovered vertices are added.
     * @param start The start vertex for the search
     * @param adder A purely functional object that adds to either the head or
     * tail of a double-ended queue
     */
    public Vertex search(Vertex start, DequeAdder adder) {
        
        Vertex v;
        start.setPredecessor(null);
        start.setDistance(0);
        this.deque.clear();
        this.deque.add(start);
        this.queueOps = 0;
        this.queueSize = 0;
        this.maxQueueSize = 0;
        while (!this.deque.isEmpty()) {
            v = (Vertex) this.deque.remove();
            this.queueOps = this.queueOps + 1;
            this.queueSize = this.queueSize - 1;
            this.currentState = (State) v;
            if (success()) {
                return v;
            }
            List<Vertex> successors = expand(v);
                Iterator iter = successors.iterator();
                while (iter.hasNext()) {
                    Vertex successor = (Vertex) iter.next();
                    successor.setDistance(v.getDistance()+1);
                    successor.setPredecessor(v);
                    adder.add(successor, this.deque);
                }
        }
       return null; 
    }
    
    private State currentState;
    private State initialState;
    private String introduction;
    private List<Move> moves;
    private int queueOps;
    private int queueSize;
    private int maxQueueSize;
    private Deque<Vertex> deque;
    private DequeAdder headAdder;
    private DequeAdder tailAdder;
    private Stack<Vertex> stack;
   
}
