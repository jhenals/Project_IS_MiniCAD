package MiniCAD.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericDocumentFilter extends DocumentFilter {
    private static final String NUMERIC_REGEX = "\\d*\\.?\\d*";

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException{
        if(string == null){
            return;
        }
        String newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
                .insert(offset, string).toString();
        if(newText.matches(NUMERIC_REGEX)){
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
        if(string == null){
            return;
        }
        String newText= new StringBuilder(fb.getDocument().getText(0,fb.getDocument().getLength()))
                .replace(offset, offset + length, string).toString();
        if (newText.matches(NUMERIC_REGEX)) {
            super.replace(fb, offset, length, string, attr);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        String newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
                .delete(offset, offset + length).toString();
        if (newText.matches(NUMERIC_REGEX)) {
            super.remove(fb, offset, length);
        }
    }
}
