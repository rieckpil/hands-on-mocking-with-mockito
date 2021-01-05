from diagrams import Cluster, Diagram
from diagrams.programming.language import Java

graph_attr = {
  "fontsize": "20",
  "bgcolor": "white"  # transparent
}

with Diagram("", direction="LR", graph_attr=graph_attr, outformat="png", filename="no-collaborator-setup"):

  with Cluster("Class Under Test  (CUT)"):
    cut = Java("StringManipulator")
