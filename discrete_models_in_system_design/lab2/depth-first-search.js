// function allowTraversal -
//   Determines whether DFS should traverse from the vertex to its neighbor
//   (along the edge). By default prohibits visiting the same vertex again.
//
//  function enterVertex - Called when DFS enters the vertex.
//
//  function leaveVertex - Called when DFS leaves the vertex.

function initCallbacks(callbacks = {}) {
  const initiatedCallback = callbacks;

  const stubCallback = () => {
  };

  const allowTraversalCallback = (
    () => {
      const seen = {};
      return ({nextVertex}) => {
        if (!seen[nextVertex.getKey()]) {
          seen[nextVertex.getKey()] = true;
          return true;
        }
        return false;
      };
    }
  )();

  initiatedCallback.allowTraversal = callbacks.allowTraversal || allowTraversalCallback;
  initiatedCallback.enterVertex = callbacks.enterVertex || stubCallback;
  initiatedCallback.leaveVertex = callbacks.leaveVertex || stubCallback;

  return initiatedCallback;
}

function depthFirstSearchRecursive(graph, currentVertex, previousVertex, callbacks) {
  callbacks.enterVertex({currentVertex, previousVertex});

  graph.getNeighbors(currentVertex).forEach((nextVertex) => {
    if (callbacks.allowTraversal({previousVertex, currentVertex, nextVertex})) {
      depthFirstSearchRecursive(graph, nextVertex, currentVertex, callbacks);
    }
  });

  callbacks.leaveVertex({currentVertex, previousVertex});
}

function depthFirstSearch(graph, startVertex, callbacks) {
  const previousVertex = null;
  depthFirstSearchRecursive(graph, startVertex, previousVertex, initCallbacks(callbacks));
}

module.exports = depthFirstSearch;
