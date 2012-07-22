/********************************************************************************
 *
 * jAddressBook - advanced address book
 *
 * Copyright (C) 2006 jAddressBook development team
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details ( see the LICENSE file ).
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 ********************************************************************************/

package gui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import util.build;

/**
 *
 * @author binary
 */
public class aboutWindow extends Dialog{
    
    /** Creates a new instance of aboutWindow */
    public aboutWindow(Shell parentShell)
{
  super(parentShell);
  //super.setShellStyle(SWT.RESIZE | SWT.DIALOG_TRIM);
}
    protected Control createDialogArea(Composite parent){
        
        parent.getShell().setText("About jAddressBook");
        Composite dlgcontent = (Composite)super.createDialogArea(parent);
        dlgcontent.setLayout(new GridLayout(1,false));
        
        Composite top=new Composite(dlgcontent,SWT.NONE);
        top.setLayout(new FillLayout());
        top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        Label top_label=new Label(top,SWT.CENTER);
        //top_label.setText("About jAddressBook");
        top_label.setImage(new Image(Display.getCurrent(),"./img/jLogo.png"));
        //top_label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        
        Composite content=new Composite(dlgcontent,SWT.NONE);
        content.setLayout(new FillLayout());
        content.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        CTabFolder tabFolder = new CTabFolder(content, SWT.BORDER);
        tabFolder.setSimple(false);
        tabFolder.setUnselectedImageVisible(false);
        tabFolder.setUnselectedCloseVisible(false);
        /*
         * Build Tab
         */
        CTabItem buildTab = new CTabItem(tabFolder,SWT.BORDER);
        buildTab.setText("Build data");
        Composite buildComp=new Composite(tabFolder,SWT.NONE);
        buildTab.setControl(buildComp);
        
        build b=new build();
        
        buildComp.setLayout(new GridLayout(2,false));
        
        new Label(buildComp,SWT.NONE).setText("Author : ");
        
        Text tauthor=new Text(buildComp,SWT.BORDER);
        tauthor.setText("Obada Denis <obadadenis@users.sf.net>");
        tauthor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tauthor.setEditable(false);
        
        new Label(buildComp,SWT.NONE).setText("Web Page : ");
        
        Text tlink=new Text(buildComp,SWT.BORDER);
        tlink.setText("http://www.jaddressbook2.sf.net");
        tlink.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tlink.setEditable(false);

        
        new Label(buildComp,SWT.NONE).setText("Version : ");

        Text tversion=new Text(buildComp,SWT.BORDER);
        tversion.setText(b.getBuildVersion()+"");
        tversion.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tversion.setEditable(false);
        
        new Label(buildComp,SWT.NONE).setText("Build count : ");
        
        Text tbcount=new Text(buildComp,SWT.BORDER);
        tbcount.setText(b.getBuild()+"");
        tbcount.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tbcount.setEditable(false);
        
        new Label(buildComp,SWT.NONE).setText("Build date : ");
        
        Text tbdate=new Text(buildComp,SWT.BORDER);
        tbdate.setText(b.getBuildDate());
        tbdate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tbdate.setEditable(false);
        
        new Label(buildComp,SWT.NONE).setText("Builded on : ");
        
        Text tbplatform=new Text(buildComp,SWT.BORDER);
        tbplatform.setText(b.getBuildPlatform());
        tbplatform.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tbplatform.setEditable(false);
      
        Label l1=new Label(buildComp,SWT.NONE);
        GridData gy=new GridData(GridData.FILL_HORIZONTAL);
        gy.horizontalSpan=2;        
        l1.setLayoutData(gy);
        l1.setText("");
        
        Composite logos=new Composite(buildComp,SWT.BORDER);
        GridData gx=new GridData(GridData.FILL_BOTH);
        gx.horizontalSpan=2;
        
        logos.setLayout(new FillLayout(SWT.HORIZONTAL));
        logos.setLayoutData(gx);
        
        new Label(logos,SWT.CENTER).setImage(new Image(Display.getCurrent(),"./img/eclipse.gif"));
        new Label(logos,SWT.CENTER).setImage(new Image(Display.getCurrent(),"./img/java.gif"));
        new Label(logos,SWT.CENTER).setImage(new Image(Display.getCurrent(),"./img/ant.gif"));
        /*
         * License tab
         */
        CTabItem licenseTab = new CTabItem(tabFolder,SWT.NONE);
        licenseTab.setText("License");
        Composite licenseComp=new Composite(tabFolder,SWT.NONE);
        licenseTab.setControl(licenseComp);
        
        licenseComp.setLayout(new FillLayout());
        Text tlicense=new Text(licenseComp,SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        tlicense.setText("License");
        tlicense.setEditable(false);
        String license="		    GNU GENERAL PUBLIC LICENSE"+
        "\n		       Version 2, June 1991"+
        "\n"+
        "\n Copyright (C) 1989, 1991 Free Software Foundation, Inc."+
        "\n                       59 Temple Place, Suite 330, Boston, MA  02111-1307  USA"+
        "\n Everyone is permitted to copy and distribute verbatim copies"+
        "\n of this license document, but changing it is not allowed."+
        "\n"+
        "\n			    Preamble"+
        "\n"+
        "\n  The licenses for most software are designed to take away your"+
        "\nfreedom to share and change it.  By contrast, the GNU General Public"+
        "\nLicense is intended to guarantee your freedom to share and change free"+
        "\nsoftware--to make sure the software is free for all its users.  This"+
        "\nGeneral Public License applies to most of the Free Software"+
        "\nFoundation's software and to any other program whose authors commit to"+
        "\nusing it.  (Some other Free Software Foundation software is covered by"+
        "\nthe GNU Library General Public License instead.)  You can apply it to"+
        "\nyour programs, too."+
        "\n"+
        "\n  When we speak of free software, we are referring to freedom, not"+
        "\nprice.  Our General Public Licenses are designed to make sure that you"+
        "\nhave the freedom to distribute copies of free software (and charge for"+
        "\nthis service if you wish), that you receive source code or can get it"+
        "\nif you want it, that you can change the software or use pieces of it"+
        "\nin new free programs; and that you know you can do these things."+
        "\n"+
        "\n  To protect your rights, we need to make restrictions that forbid"+
        "\nanyone to deny you these rights or to ask you to surrender the rights."+
        "\nThese restrictions translate to certain responsibilities for you if you"+
        "\ndistribute copies of the software, or if you modify it."+
        "\n"+
        "\n  For example, if you distribute copies of such a program, whether"+
        "\ngratis or for a fee, you must give the recipients all the rights that"+
        "\nyou have.  You must make sure that they, too, receive or can get the"+
        "\nsource code.  And you must show them these terms so they know their"+
        "\nrights."+
        "\n"+
        "\n  We protect your rights with two steps: (1) copyright the software, and"+
        "\n(2) offer you this license which gives you legal permission to copy,"+
        "\ndistribute and/or modify the software."+
        "\n"+
        "\n  Also, for each author's protection and ours, we want to make certain"+
        "\nthat everyone understands that there is no warranty for this free"+
        "\nsoftware.  If the software is modified by someone else and passed on, we"+
        "\nwant its recipients to know that what they have is not the original, so"+
        "\nthat any problems introduced by others will not reflect on the original"+
        "\nauthors' reputations."+
        "\n"+
        "\n  Finally, any free program is threatened constantly by software"+
        "\npatents.  We wish to avoid the danger that redistributors of a free"+
        "\nprogram will individually obtain patent licenses, in effect making the"+
        "\nprogram proprietary.  To prevent this, we have made it clear that any"+
        "\npatent must be licensed for everyone's free use or not licensed at all."+
        "\n"+
        "\n  The precise terms and conditions for copying, distribution and"+
        "\nmodification follow."+
        "\n"+
        "\n		    GNU GENERAL PUBLIC LICENSE"+
        "\n   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION"+
        "\n"+
        "\n  0. This License applies to any program or other work which contains"+
        "\na notice placed by the copyright holder saying it may be distributed"+
        "\nunder the terms of this General Public License.  The \"Program\", below,"+
        "\nrefers to any such program or work, and a \"work based on the Program\""+
        "\nmeans either the Program or any derivative work under copyright law:"+
        "\nthat is to say, a work containing the Program or a portion of it,"+
        "\neither verbatim or with modifications and/or translated into another"+
        "\nlanguage.  (Hereinafter, translation is included without limitation in"+
        "\nthe term \"modification\".)  Each licensee is addressed as \"you\"."+
        "\n"+
        "\nActivities other than copying, distribution and modification are not"+
        "\ncovered by this License; they are outside its scope.  The act of"+
        "\nrunning the Program is not restricted, and the output from the Program"+
        "\nis covered only if its contents constitute a work based on the"+
        "\nProgram (independent of having been made by running the Program)."+
        "\nWhether that is true depends on what the Program does."+
        "\n"+
        "\n  1. You may copy and distribute verbatim copies of the Program's"+
        "\nsource code as you receive it, in any medium, provided that you"+
        "\nconspicuously and appropriately publish on each copy an appropriate"+
        "\ncopyright notice and disclaimer of warranty; keep intact all the"+
        "\nnotices that refer to this License and to the absence of any warranty;"+
        "\nand give any other recipients of the Program a copy of this License"+
        "\nalong with the Program."+
        "\n"+
        "\nYou may charge a fee for the physical act of transferring a copy, and"+
        "\nyou may at your option offer warranty protection in exchange for a fee."+
        "\n"+
        "\n  2. You may modify your copy or copies of the Program or any portion"+
        "\nof it, thus forming a work based on the Program, and copy and"+
        "\ndistribute such modifications or work under the terms of Section 1"+
        "\nabove, provided that you also meet all of these conditions:"+
        "\n"+
        "\n    a) You must cause the modified files to carry prominent notices"+
        "\n    stating that you changed the files and the date of any change."+
        "\n"+
        "\n    b) You must cause any work that you distribute or publish, that in"+
        "\n    whole or in part contains or is derived from the Program or any"+
        "\n    part thereof, to be licensed as a whole at no charge to all third"+
        "\n    parties under the terms of this License."+
        "\n"+
        "\n    c) If the modified program normally reads commands interactively"+
        "\n    when run, you must cause it, when started running for such"+
        "\n    interactive use in the most ordinary way, to print or display an"+
        "\n    announcement including an appropriate copyright notice and a"+
        "\n    notice that there is no warranty (or else, saying that you provide"+
        "\n    a warranty) and that users may redistribute the program under"+
        "\n    these conditions, and telling the user how to view a copy of this"+
        "\n    License.  (Exception: if the Program itself is interactive but"+
        "\n    does not normally print such an announcement, your work based on"+
        "\n    the Program is not required to print an announcement.)"+
        "\n"+
        "\nThese requirements apply to the modified work as a whole.  If"+
        "\nidentifiable sections of that work are not derived from the Program,"+
        "\nand can be reasonably considered independent and separate works in"+
        "\nthemselves, then this License, and its terms, do not apply to those"+
        "\nsections when you distribute them as separate works.  But when you"+
        "\ndistribute the same sections as part of a whole which is a work based"+
        "\non the Program, the distribution of the whole must be on the terms of"+
        "\nthis License, whose permissions for other licensees extend to the"+
        "\nentire whole, and thus to each and every part regardless of who wrote it."+
        "\n"+
        "\nThus, it is not the intent of this section to claim rights or contest"+
        "\nyour rights to work written entirely by you; rather, the intent is to"+
        "\nexercise the right to control the distribution of derivative or"+
        "\ncollective works based on the Program."+
        "\n"+
        "\nIn addition, mere aggregation of another work not based on the Program"+
        "\nwith the Program (or with a work based on the Program) on a volume of"+
        "\na storage or distribution medium does not bring the other work under"+
        "\nthe scope of this License."+
        "\n"+
        "\n  3. You may copy and distribute the Program (or a work based on it,"+
        "\nunder Section 2) in object code or executable form under the terms of"+
        "\nSections 1 and 2 above provided that you also do one of the following:"+
        "\n"+
        "\n    a) Accompany it with the complete corresponding machine-readable"+
        "\n    source code, which must be distributed under the terms of Sections"+
        "\n    1 and 2 above on a medium customarily used for software interchange; or,"+
        "\n"+
        "\n    b) Accompany it with a written offer, valid for at least three"+
        "\n    years, to give any third party, for a charge no more than your"+
        "\n    cost of physically performing source distribution, a complete"+
        "\n    machine-readable copy of the corresponding source code, to be"+
        "\n    distributed under the terms of Sections 1 and 2 above on a medium"+
        "\n    customarily used for software interchange; or,"+
        "\n"+
        "\n    c) Accompany it with the information you received as to the offer"+
        "\n    to distribute corresponding source code.  (This alternative is"+
        "\n    allowed only for noncommercial distribution and only if you"+
        "\n    received the program in object code or executable form with such"+
        "\n    an offer, in accord with Subsection b above.)"+
        "\n"+
        "\nThe source code for a work means the preferred form of the work for"+
        "\nmaking modifications to it.  For an executable work, complete source"+
        "\ncode means all the source code for all modules it contains, plus any"+
        "\nassociated interface definition files, plus the scripts used to"+
        "\ncontrol compilation and installation of the executable.  However, as a"+
        "\nspecial exception, the source code distributed need not include"+
        "\nanything that is normally distributed (in either source or binary"+
        "\nform) with the major components (compiler, kernel, and so on) of the"+
        "\noperating system on which the executable runs, unless that component"+
        "\nitself accompanies the executable."+
        "\n"+
        "\nIf distribution of executable or object code is made by offering"+
        "\naccess to copy from a designated place, then offering equivalent"+
        "\naccess to copy the source code from the same place counts as"+
        "\ndistribution of the source code, even though third parties are not"+
        "\ncompelled to copy the source along with the object code."+
        "\n"+
        "\n  4. You may not copy, modify, sublicense, or distribute the Program"+
        "\nexcept as expressly provided under this License.  Any attempt"+
        "\notherwise to copy, modify, sublicense or distribute the Program is"+
        "\nvoid, and will automatically terminate your rights under this License."+
        "\nHowever, parties who have received copies, or rights, from you under"+
        "\nthis License will not have their licenses terminated so long as such"+
        "\nparties remain in full compliance."+
        "\n"+
        "\n  5. You are not required to accept this License, since you have not"+
        "\nsigned it.  However, nothing else grants you permission to modify or"+
        "\ndistribute the Program or its derivative works.  These actions are"+
        "\nprohibited by law if you do not accept this License.  Therefore, by"+
        "\nmodifying or distributing the Program (or any work based on the"+
        "\nProgram), you indicate your acceptance of this License to do so, and"+
        "\nall its terms and conditions for copying, distributing or modifying"+
        "\nthe Program or works based on it."+
        "\n"+
        "\n  6. Each time you redistribute the Program (or any work based on the"+
        "\nProgram), the recipient automatically receives a license from the"+
        "\noriginal licensor to copy, distribute or modify the Program subject to"+
        "\nthese terms and conditions.  You may not impose any further"+
        "\nrestrictions on the recipients' exercise of the rights granted herein."+
        "\nYou are not responsible for enforcing compliance by third parties to"+
        "\nthis License."+
        "\n"+
        "\n  7. If, as a consequence of a court judgment or allegation of patent"+
        "\ninfringement or for any other reason (not limited to patent issues),"+
        "\nconditions are imposed on you (whether by court order, agreement or"+
        "\notherwise) that contradict the conditions of this License, they do not"+
        "\nexcuse you from the conditions of this License.  If you cannot"+
        "\ndistribute so as to satisfy simultaneously your obligations under this"+
        "\nLicense and any other pertinent obligations, then as a consequence you"+
        "\nmay not distribute the Program at all.  For example, if a patent"+
        "\nlicense would not permit royalty-free redistribution of the Program by"+
        "\nall those who receive copies directly or indirectly through you, then"+
        "\nthe only way you could satisfy both it and this License would be to"+
        "\nrefrain entirely from distribution of the Program."+
        "\n"+
        "\nIf any portion of this section is held invalid or unenforceable under"+
        "\nany particular circumstance, the balance of the section is intended to"+
        "\napply and the section as a whole is intended to apply in other"+
        "\ncircumstances."+
        "\n"+
        "\nIt is not the purpose of this section to induce you to infringe any"+
        "\npatents or other property right claims or to contest validity of any"+
        "\nsuch claims; this section has the sole purpose of protecting the"+
        "\nintegrity of the free software distribution system, which is"+
        "\nimplemented by public license practices.  Many people have made"+
        "\ngenerous contributions to the wide range of software distributed"+
        "\nthrough that system in reliance on consistent application of that"+
        "\nsystem; it is up to the author/donor to decide if he or she is willing"+
        "\nto distribute software through any other system and a licensee cannot"+
        "\nimpose that choice."+
        "\n"+
        "\nThis section is intended to make thoroughly clear what is believed to"+
        "\nbe a consequence of the rest of this License."+
        "\n"+
        "\n  8. If the distribution and/or use of the Program is restricted in"+
        "\ncertain countries either by patents or by copyrighted interfaces, the"+
        "\noriginal copyright holder who places the Program under this License"+
        "\nmay add an explicit geographical distribution limitation excluding"+
        "\nthose countries, so that distribution is permitted only in or among"+
        "\ncountries not thus excluded.  In such case, this License incorporates"+
        "\nthe limitation as if written in the body of this License."+
        "\n"+
        "\n  9. The Free Software Foundation may publish revised and/or new versions"+
        "\nof the General Public License from time to time.  Such new versions will"+
        "\nbe similar in spirit to the present version, but may differ in detail to"+
        "\naddress new problems or concerns."+
        "\n"+
        "\nEach version is given a distinguishing version number.  If the Program"+
        "\nspecifies a version number of this License which applies to it and \"any"+
        "\nlater version\", you have the option of following the terms and conditions"+
        "\neither of that version or of any later version published by the Free"+
        "\nSoftware Foundation.  If the Program does not specify a version number of"+
        "\nthis License, you may choose any version ever published by the Free Software"+
        "\nFoundation."+
        "\n"+
        "\n  10. If you wish to incorporate parts of the Program into other free"+
        "\nprograms whose distribution conditions are different, write to the author"+
        "\nto ask for permission.  For software which is copyrighted by the Free"+
        "\nSoftware Foundation, write to the Free Software Foundation; we sometimes"+
        "\nmake exceptions for this.  Our decision will be guided by the two goals"+
        "\nof preserving the free status of all derivatives of our free software and"+
        "\nof promoting the sharing and reuse of software generally."+
        "\n"+
        "\n			    NO WARRANTY"+
        "\n"+
        "\n  11. BECAUSE THE PROGRAM IS LICENSED FREE OF CHARGE, THERE IS NO WARRANTY"+
        "\nFOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE LAW.  EXCEPT WHEN"+
        "\nOTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR OTHER PARTIES"+
        "\nPROVIDE THE PROGRAM \"AS IS\" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED"+
        "\nOR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF"+
        "\nMERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.  THE ENTIRE RISK AS"+
        "\nTO THE QUALITY AND PERFORMANCE OF THE PROGRAM IS WITH YOU.  SHOULD THE"+
        "\nPROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF ALL NECESSARY SERVICING,"+
        "\nREPAIR OR CORRECTION."+
        "\n"+
        "\n  12. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING"+
        "\nWILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY MODIFY AND/OR"+
        "\nREDISTRIBUTE THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES,"+
        "\nINCLUDING ANY GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING"+
        "\nOUT OF THE USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED"+
        "\nTO LOSS OF DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY"+
        "\nYOU OR THIRD PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER"+
        "\nPROGRAMS), EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE"+
        "\nPOSSIBILITY OF SUCH DAMAGES."+
        "\n"+
        "\n		     END OF TERMS AND CONDITIONS"+
        "\n"+
        "\n	    How to Apply These Terms to Your New Programs"+
        "\n"+
        "\n  If you develop a new program, and you want it to be of the greatest"+
        "\npossible use to the public, the best way to achieve this is to make it"+
        "\nfree software which everyone can redistribute and change under these terms."+
        "\n"+
        "\n  To do so, attach the following notices to the program.  It is safest"+
        "\nto attach them to the start of each source file to most effectively"+
        "\nconvey the exclusion of warranty; and each file should have at least"+
        "\nthe \"copyright\" line and a pointer to where the full notice is found."+
        "\n"+
        "\n    <one line to give the program's name and a brief idea of what it does.>"+
        "\n    Copyright (C) <year>  <name of author>"+
        "\n"+
        "\n    This program is free software; you can redistribute it and/or modify"+
        "\n    it under the terms of the GNU General Public License as published by"+
        "\n    the Free Software Foundation; either version 2 of the License, or"+
        "\n    (at your option) any later version."+
        "\n"+
        "\n    This program is distributed in the hope that it will be useful,"+
        "\n    but WITHOUT ANY WARRANTY; without even the implied warranty of"+
        "\n    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the"+
        "\n    GNU General Public License for more details."+
        "\n"+
        "\n    You should have received a copy of the GNU General Public License"+
        "\n    along with this program; if not, write to the Free Software"+
        "\n    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA"+
        "\n"+
        "\n"+
        "\nAlso add information on how to contact you by electronic and paper mail."+
        "\n"+
        "\nIf the program is interactive, make it output a short notice like this"+
        "\nwhen it starts in an interactive mode:"+
        "\n"+
        "\n    Gnomovision version 69, Copyright (C) year name of author"+
        "\n    Gnomovision comes with ABSOLUTELY NO WARRANTY; for details type `show w'."+
        "\n    This is free software, and you are welcome to redistribute it"+
        "\n    under certain conditions; type `show c' for details."+
        "\n"+
        "\nThe hypothetical commands `show w' and `show c' should show the appropriate"+
        "\nparts of the General Public License.  Of course, the commands you use may"+
        "\nbe called something other than `show w' and `show c'; they could even be"+
        "\nmouse-clicks or menu items--whatever suits your program."+
        "\n"+
        "\nYou should also get your employer (if you work as a programmer) or your"+
        "\nschool, if any, to sign a \"copyright disclaimer\" for the program, if"+
        "\nnecessary.  Here is a sample; alter the names:"+
        "\n"+
        "\n  Yoyodyne, Inc., hereby disclaims all copyright interest in the program"+
        "\n  `Gnomovision' (which makes passes at compilers) written by James Hacker."+
        "\n"+
        "\n  <signature of Ty Coon>, 1 April 1989"+
        "\n  Ty Coon, President of Vice"+
        "\n"+
        "\nThis General Public License does not permit incorporating your program into"+
        "\nproprietary programs.  If your program is a subroutine library, you may"+
        "\nconsider it more useful to permit linking proprietary applications with the"+
        "\nlibrary.  If this is what you want to do, use the GNU Library General"+
        "\nPublic License instead of this License.";


        tlicense.setText(license);
        
        
        
        return parent;
    }
    
    protected void createButtonsForButtonBar(Composite parent){
    createButton(parent, 1, "Ok", true).setImage(new Image(Display.getCurrent(),"./img/artwork/ok.png"));
    //Setup Window size to display Buttons
    parent.getShell().setSize(400,500);
    }
    protected void buttonPressed(int buttonId){
    super.buttonPressed(buttonId);
  } 

}
