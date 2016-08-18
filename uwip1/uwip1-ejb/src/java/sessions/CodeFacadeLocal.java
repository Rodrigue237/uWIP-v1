/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Box;
import entities.Code;
import entities.Credits;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface CodeFacadeLocal {

    void create(Code code);

    void edit(Code code);

    void remove(Code code);

    Code find(Object id);

    List<Code> findAll();

    List<Code> findRange(int[] range);

    int count();

    List<Box> findByInstalled();

    public int findIdBoxOfCode(int numBoxUSSD);

    public int findIdClientOfCode(int numBoxUSSD);

    public int findIdOrganizationOfCode(int numBoxUSSD);

    public int findIdDistributorOfCode(int distribuniqnumberUSSD);

    public int findKey1(int numBoxCode);

    public int findKey2(int numBoxCode);

    public int findKey3(int numBoxCode);

    public int findNumPcb(int numBoxCode);

    public String findPaygIdprod(int numBoxCode);

    public String findPrix1Jours(int numBoxCode);

    public int findTotalTimePaid(int numBoxCode);

    public int findToUpdateKeys(int key3Code, int nextKey3, long idBoxCode, Date dateTransact, Date estimEndDate, BigInteger lastValueTime, BigInteger newLeftTimePaid, BigInteger newTotalTimePaid);

    public Date findLastEndDate(int numBoxCode);

    public Date findLastEndDateForAlert(int numBoxAlert);

    public int findLastTotalTimePaid(int numBoxCode);

    public String getContents(String urlFM, String encodeType);
}
