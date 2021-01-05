from diagrams import Cluster, Diagram
from diagrams.aws.integration import SQS
from diagrams.onprem.client import Users
from diagrams.onprem.compute import Server
from diagrams.onprem.database import PostgreSQL
from diagrams.onprem.network import Internet
from diagrams.programming.framework import Spring, React
from diagrams.programming.language import Java

graph_attr = {
  "fontsize": "20",
  "bgcolor": "white"  # transparent
}

with Diagram("", direction="LR", graph_attr=graph_attr, outformat="png", filename="advanced-setup"):

  database = PostgreSQL("Database")
  collaborator = Java("UserRepository")

  with Cluster("Class Under Test  (CUT) "):
    cut = Java("RegistrationService")

  second_collaborator = Java("BannedUsersClient")
  api = Internet("Remote System")

  database << collaborator
  collaborator << cut
  second_collaborator << cut
  api << second_collaborator
