package pattern.template;

import javax.naming.OperationNotSupportedException;

/**
 * @author payno
 * @date 2019/12/16 19:05
 * @description
 */
public class AbstractOffice implements Office{
    @Override
    public final void open()  throws OperationNotSupportedException{
       if(getResource()){
           encodeResource();
           displayView();
       }
    }

    @Override
    public final void close() throws OperationNotSupportedException{
        closeView();
        releaseResource();
    }

    protected boolean getResource() throws OperationNotSupportedException{
        throw new OperationNotSupportedException();
    }

    protected void encodeResource() throws OperationNotSupportedException{
        throw new OperationNotSupportedException();
    }

    protected void displayView()  throws OperationNotSupportedException{
        throw new OperationNotSupportedException();
    }

    protected void closeView() throws OperationNotSupportedException{
        throw new OperationNotSupportedException();
    }

    protected void releaseResource() throws OperationNotSupportedException{
        throw new OperationNotSupportedException();
    }
}
