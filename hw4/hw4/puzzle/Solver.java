package hw4.puzzle;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import edu.princeton.cs.algs4.MinPQ;

public class Solver
{
    private class SearchNode {
        private WorldState state;
        private int moves = 0;
        private SearchNode prev;

        public SearchNode(WorldState state, int moves, SearchNode prev) {
            this.state = state;
            this.moves = moves;
            this.prev = prev;

        }

        private class NodeComparator implements Comparator<SearchNode> {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                ;
                int o1Edtg = getEdtg(o1);
                int o2Edtg = getEdtg(o2);
                int o1priority = o1.moves + o1Edtg;
                int o2priority = o2.moves + o2Edtg;
                return o1priority - o2priority;


            }

            private int getEdtg(SearchNode sn) {
                if (!edtgCaches.containsKey(sn.state)) {
                    edtgCaches.put(sn.state, sn.state.estimatedDistanceToGoal());
                }
                return edtgCaches.get(sn.state);
            }


        }
    }
        private Map<WorldState,Integer> edtgCaches=new HashMap<>();
        private List<WorldState>solution =new ArrayList<>();
        public Solver(WorldState initial) {
            MinPQ<SearchNode> pq = new MinPQ<>();
            SearchNode curnode = new SearchNode(initial, 0, null);
            pq.insert(curnode);
            SearchNode goalnode=null;

            while (!pq.isEmpty()) {
                SearchNode v = pq.delMin();
                if (v.state.isGoal()) {
                    goalnode = v;
                    break;
                }
                for (WorldState nextstate : curnode.state.neighbors()) {
                    SearchNode s = new SearchNode(nextstate, v.moves + 1, v);
                    if (v.prev != null && nextstate.equals(v.prev.state)) {
                        continue;
                    }
                    pq.insert(s);
                }


            }

            Stack<WorldState> path = new Stack<>();
            for (SearchNode n = goalnode;n!=null;n=goalnode.prev )
            {
             path.push(n.state);
            }
            while (!path.isEmpty())
            {
                solution.add(path.pop());
            }



        }
        public int moves(){
            return  solution.size()-1;
        }
        public Iterable<WorldState> solution(){
            return solution;
        }







}
