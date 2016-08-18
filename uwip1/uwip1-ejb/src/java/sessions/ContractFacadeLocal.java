/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Contract;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface ContractFacadeLocal {

    void create(Contract contract);

    void edit(Contract contract);

    void remove(Contract contract);

    Contract find(Object id);

    List<Contract> findAll();

    List<Contract> findRange(int[] range);

    int count();

    List<Contract> findByIdcontract(Long idcontract);

    public Long findMaxIdContract();

    public int findLastClientContract(long reqMaxIdContract);

    public int findLastBoxContract(long reqMaxIdContract);

    public int updateBalance(long moneyBalance, long reqMaxIdContract);

    public int updateDesinstallDate(Date desinstallDate, String desactivStatus, long idlastcontratBox);
}
