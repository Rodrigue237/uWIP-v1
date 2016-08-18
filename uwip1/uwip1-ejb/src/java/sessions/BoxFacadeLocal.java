/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Box;
import entities.Client;
import entities.Organization;
import entities.Townlayer;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface BoxFacadeLocal {

    void create(Box box);

    void edit(Box box);

    void remove(Box box);

    Box find(Object id);

    List<Box> findAll();

    List<Box> findRange(int[] range);

    int count();

    List<Box> findByMapped();

    List<Box> findByLibre();

    List<Box> findByMaintenance();

    List<Box> findByIndispo(String magDispo);

    //List <Box> findUpdate(Long idboxinstal);
    public int findNumPcb(int numBoxCode);

    public int findToUpdateStMarquer(long idboxinstal, String boxActivated);

    public int findToUpdateLatiAndLong(double latitudeinstal, double longitudeinstal, long idboxinstal);

    public int findToUpdateClientBox(long idboxinstal, Client idclient);

    public int findToUpdateOrganizationBox(long idboxinstal, Organization idorganization);

    public int findCopieDouble(String numBoxSerieSaisi);

    public int findToChangeStMarquer1(long idboxinstal, String magHS, int numeroBox);

    public int findToInitNumBox(int numInstall, long idboxinstal);

    public int findToDesactivateClientBox(long idboxinstal, Client idclient);

    public int findKey1(int idboxselect);

    public int findKey2(int idboxselect);

    public int findKey3(int idboxselect);

    public int findNumBox(int idboxselect);

    public int findTotalInstall(long idboxinstal);

    public int changeTotalInstall(int totalInstall, long idboxinstal, String userOfSystem, int idOfLastContract, int phoneUserSystem);

    public int updateFirstInstallDate(long idboxinstal, Date contratInstallDate);

    public int updateLastInstallDate(long idboxinstal, Date contratInstallDate);

    //public int findIdClientOut(int numboxOut);
    //public int findIdOrganizationOut(int numboxOut);
    //public int findIdTownlayerOut(int numboxOut);
    public int remplaceBox(int numboxOut, int idlastContractOut, int totatInstallIn, double latitudeOut, double longitudeOut, String countryOut, Date newlastinstallDate, Date lasttransactOut, BigInteger lasttimecodevalueOut, BigInteger totaltimepaidOut, BigInteger estimlefttimepaidOut, int marquerstatusOut, String userofsystemtOut, Date estimatedenddateOut, String marquerstyleOut, String atelierOut, Townlayer idtownlayerOut, Organization idorganizationOut, Client idclientOut, long idboxeIn);
}
