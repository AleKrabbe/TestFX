package br.com.calctdd.calctddmaven.model;

import br.com.calctdd.calctddmaven.controller.FXMLDocumentController.Operadores;
import calctdd.exceptions.ExpressionNotCompleteException;

/**
 * @author Alexandre
 */
public class Expression {
    
    private Entry left, right;
    private Operadores operador;

    public Expression() {
        this.left = null;
        this.operador = null;
        this.right = null;
    }
    
    public Expression(Entry left) {
        this.left = left;
        this.operador = null;
        this.right = null;
    }
    
    public Expression(Entry left, Operadores op) {
        this.left = left;
        this.operador = op;
        this.right = null;
    }
    
    public Expression(Entry left, Operadores op, Entry right) {
        this.left = left;
        this.operador = op;
        this.right = right;
    }
    
    public double execute() throws ExpressionNotCompleteException{
        double result = 0;
        if (getLeft() == null || getRight() == null || getOperador() == null) {
            throw new ExpressionNotCompleteException("Expressao incompleta");
        } else {
            switch(getOperador()) {
                case SUM:
                    result = getLeft().getValor() + getRight().getValor();
                    break;
                case SUB:
                    result = getLeft().getValor() - getRight().getValor();
                    break;
                case DIV:
                    result = getLeft().getValor() / getRight().getValor();
                    break;
                case MUL:
                    result = getLeft().getValor() * getRight().getValor();
                    break;
            }
        }
        return result;
    }
    
    public boolean isComplete() {
        if (getLeft() == null || getRight() == null || getOperador() == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean hasLeft() {
        if (getLeft() != null && getOperador() != null) {
            return true;
        } else {
            return false;
        }
    }

    public void setLeft(Entry left) {
        this.left = left;
    }

    public void setRight(Entry right) {
        this.right = right;
    }

    public void setOperador(Operadores operador) {
        this.operador = operador;
    }
    
    public Entry getLeft() {
        return left;
    }

    public Entry getRight() {
        return right;
    }

    public Operadores getOperador() {
        return operador;
    }
    
    
}
