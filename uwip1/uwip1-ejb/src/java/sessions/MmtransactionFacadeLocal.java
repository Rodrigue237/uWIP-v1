/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mmtransaction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface MmtransactionFacadeLocal {

    void create(Mmtransaction mmtransaction);

    void edit(Mmtransaction mmtransaction);

    void remove(Mmtransaction mmtransaction);

    Mmtransaction find(Object id);

    List<Mmtransaction> findAll();

    List<Mmtransaction> findRange(int[] range);

    int count();

    public int findCopieDouble(String idNewTransactMoney);

    public int findNumPcb(int numBoxCode);

    public int maxIdPlus1();
}
