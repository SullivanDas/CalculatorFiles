
/**
 *
 * @author David Sullivan
 */
public class ShuntingYard {
    
    
    public ShuntingYard(){}
    
    /**
     * changes an infix expression to postfix
     * 
     * @param equation the equation to change to postfix
     * @return returns a postfix in an arrayqueue
     */
    public ArrayQueue infixToPost(String equation){
        
        String newEquation = splitEquation(equation);
        newEquation = newEquation.trim();
        
        String[] tokens = newEquation.split(" ");
        
        int count = 0;
        
        for(String s : tokens){
            count++;
        }
        
        ArrayQueue<String> initial = new ArrayQueue<>(count);
        
        for(String s : tokens){
            if(!s.isEmpty()){
                initial.enqueue(s);
            }
        }
        if(initial.size() == 1){
            try{
                Integer i = Integer.parseInt(initial.first());
            }
            catch(NumberFormatException nfe){
                throw new IllegalStateException();
            }
            
        }
        return shuntingAssignment(initial);
        
    }
   
    public String splitEquation(String equation){
        StringBuilder newEquation = new StringBuilder(equation);
        for(int i = 0; i < newEquation.length(); i++){
            boolean isDigit = Character.isDigit(newEquation.charAt(i));
            
            if((int)newEquation.charAt(i) != 46 && !isDigit){
                if(!(i == 0 && (int)newEquation.charAt(i) == 45)){
                    if(!(!(i<2) && (int)newEquation.charAt(i-2) == 40 && (int)newEquation.charAt(i) == 45)){
                        newEquation.insert(i, " ");
                        newEquation.insert((i + 2), " ");
                        i+=2;
                    }

                }
            }
        }
        
        return newEquation.toString();
    }
    
    private ArrayQueue shuntingAssignment(ArrayQueue initial){
 
        ArrayStack operators = new ArrayStack(initial.size());
        ArrayQueue operands = new ArrayQueue(initial.size());
        
        boolean isSearchingCloseP = false;
        
        while(!(initial.isEmpty())){
            
            //handle parenthesis 
            if(initial.first().equals("(")){
                isSearchingCloseP = true;
                initial.dequeue();
            }
            if(initial.first().equals(")")){
                isSearchingCloseP = false;
                initial.dequeue();
                operands.enqueue(operators.pop());
            }

            //check if the token is double 
            try{

                if(!initial.isEmpty()){
                    double operand = Double.parseDouble((String) initial.first());
                    operands.enqueue(initial.dequeue());
                }
            }
            //catches if operator 
            catch(NumberFormatException nfe){

                

                if(!operators.isEmpty()){
                    
                    int newOp = getIntRepresentation((String) initial.first());

                    int currentOp = getIntRepresentation((String) operators.top());
                    
                    while(newOp <= currentOp){
                        operands.enqueue(operators.pop());
                        
                        if(operators.isEmpty()) break;
                        
                        newOp = getIntRepresentation((String) initial.first());
                        currentOp = getIntRepresentation((String) operators.top());
                    }
                    
                }
                
                operators.push(initial.dequeue());

            }
            
        }

        while(!(operators.isEmpty())){

            operands.enqueue(operators.pop());
        }
        
        if(isSearchingCloseP) throw new IllegalStateException("Missing parenthesis");
        return operands;
    }
    
    /**
     * gets an integer representation of an operator
     * @param s the string operator
     * @return the value of the operator as an int for comparing
     */
    private int getIntRepresentation(String s){
        int i;
        
        switch(s){
            case "/":
            case "*":
                i = 1;
                break;
            case "+":
            case "-":
                i = 0;
                break;
            default:
                throw new IllegalStateException("Not a operator");
        }
        
        return i;
    }
    
    /**
     * creates a tree from a postorder queue
     * @param initial the queue containing postorder expression
     * @return returns a tree representation of the expression
     */
    public LinkedBinaryTree buildTree(ArrayQueue initial){
        ArrayStack<LinkedBinaryTree> operands = new ArrayStack(initial.size());
        


        while(!initial.isEmpty()){
            
            try{
                double operand = Double.parseDouble((String) initial.first());
                LinkedBinaryTree<String> baseTree = new LinkedBinaryTree<>();
                baseTree.addRoot((String) initial.dequeue());
                operands.push(baseTree);
                
            }
            catch(NumberFormatException nfe){

             
                try{
                    LinkedBinaryTree<String> baseTree = new LinkedBinaryTree<>();
                    baseTree.addRoot((String) initial.dequeue());

                    LinkedBinaryTree<String> temp2 = operands.pop();
                    LinkedBinaryTree<String> temp1 = operands.pop();


                    baseTree.attach(baseTree.root(), temp1, temp2);
                    operands.push(baseTree);
                }
                catch(NullPointerException npe){
                    throw new IllegalStateException();
                }

            }
            
        }
        
        if(operands.size() > 1) throw new IllegalStateException();
        
        LinkedBinaryTree lbt = operands.pop();
        
        return lbt;
    }

    public String evaulate(ArrayQueue<String> initial){
        ArrayStack<String> operands = new ArrayStack(initial.size());
        
        while(!initial.isEmpty()){
            
            try{
                double operand = Double.parseDouble(initial.first());
                operands.push(initial.dequeue());
            }
            catch(NumberFormatException nfe){
                try{
                double value = stringToDouble(initial.dequeue(), operands.pop(), operands.pop());
                operands.push(Double.toString(value));
                }
                catch(NumberFormatException nfe2){
                    throw new IllegalStateException();
                }
            }
        }
        
        return operands.pop();
    }
    
    public double stringToDouble(String operator, String operand2, String operand1){
        double value = 0;
        
        switch(operator){
            case "+":
                value = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                break;
            case "-":
                value = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                break;
            case "*":
                value = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                break;
            case "/":
                value = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                break;
        }
        
        return value;
    }
  
}
