from diagrams import Cluster, Diagram, Edge
from diagrams.onprem.database import PostgreSQL
from diagrams.programming.language import Java

graph_attr = {
  "fontsize": "20",
  "bgcolor": "white"  # transparent
}

with Diagram("", direction="LR", graph_attr=graph_attr, outformat="png", filename="test-basic-setup"):

  database = PostgreSQL("Database")
  collaborator = Java("UserRepository")

  fake_collaborator = Java("FakeUserRepository")

  with Cluster("Class Under Test  (CUT) "):
    cut = Java("RegistrationService")

  database << collaborator
  collaborator << Edge(color="red", style="dotted") << cut
  fake_collaborator << cut

