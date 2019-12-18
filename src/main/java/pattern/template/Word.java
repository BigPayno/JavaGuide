package pattern.template;

import javax.naming.OperationNotSupportedException;

/**
 * @author payno
 * @date 2019/12/16 19:13
 * @description
 */
public class Word extends AbstractOffice{
    @Override
    protected boolean getResource() throws OperationNotSupportedException {
        return super.getResource();
    }

    @Override
    protected void encodeResource() throws OperationNotSupportedException {
        super.encodeResource();
    }

    @Override
    protected void displayView() throws OperationNotSupportedException {
        super.displayView();
    }

    @Override
    protected void closeView() throws OperationNotSupportedException {
        super.closeView();
    }

    @Override
    protected void releaseResource() throws OperationNotSupportedException {
        super.releaseResource();
    }
}
