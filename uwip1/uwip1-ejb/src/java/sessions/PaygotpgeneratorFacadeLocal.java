/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Paygotpgenerator;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Seanjackson
 */
@Local
public interface PaygotpgeneratorFacadeLocal {

    void create(Paygotpgenerator paygotpgenerator);

    void edit(Paygotpgenerator paygotpgenerator);

    void remove(Paygotpgenerator paygotpgenerator);

    Paygotpgenerator find(Object id);

    List<Paygotpgenerator> findAll();

    List<Paygotpgenerator> findRange(int[] range);

    int count();

    public int findDataLength(String filtreCount);

    List<Paygotpgenerator> findAllFreeToInstall(String filtreNoInstallBox, String filtreYesINITBox);
    
    List<Paygotpgenerator> findAllFreeToInitialise(String filtreNoInstallBox, String filtreNoINITBox);

    public String findHRoot(String idPAYGprod);

    public int findCurrentCindex(String idPAYGprod);

    public int findCDT(String idPAYGprod);
    
    public int findMaxHCJ(String idPAYGprod);

    public int findToUpdateCDT(String idPAYGprod, int hcj_forms);
    
    public int findToUpdateFirstCode(String idPAYGprod, String codeGeneratedByOMNI);
    
    public int findToUpdateDateInitFirst(String idPAYGprod, Date dateTransact);
    
    public int findToUpdateInitstatus(String idPAYGprod, String filtreYesINITBox);

    public int updateBoxInstallInfo(String filtrePAYGmode, String filtreYesInstallBox, String idOfPAYGprod);
    
    public int updateLastMaintenanceDate(Date datePrevueInstall, String idPAYGprod);
}
