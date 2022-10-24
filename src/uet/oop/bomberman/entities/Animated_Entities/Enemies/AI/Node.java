package uet.oop.bomberman.entities.Animated_Entities.Enemies.AI;

public class Node {
    public int g; // khoang cach dinh dau den hien tai
    public int f; // h + g
    public int h; // duong di ngan nhat
    public int color; // dinh da qua
    public Node parent; // dinh cha
    public int row;
    public int col;
    public boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Node(int row, int col){
        super();
        this.row = row;
        this.col = col;
    }
    public void calHeuristics(Node finalNode){
        this.h = Math.abs(finalNode.getRow() - getRow())
                + Math.abs(finalNode.getCol() - getCol());
    }
    public void calFinalCost(){
        int finalCost = getG() + getH();
        setF(finalCost);
    }
    public boolean equals(Object arg0){
        Node other = (Node) arg0;
        return this.getRow() == other.getRow() &&
                this.getCol() == other.getCol();
    }
    public void setNodeData(Node currentNode){
        int gCost = currentNode.getG();
        setParent(currentNode);
        setG(gCost);
        calFinalCost();
    }
    public boolean checkPath(Node currentNode){
        int gCost = currentNode.getG();
        if(gCost < getG()){
            setNodeData(currentNode);
            return true;
        }
        return false;
    }
}
