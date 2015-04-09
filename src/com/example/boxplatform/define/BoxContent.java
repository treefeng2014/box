package com.example.boxplatform.define;

public class BoxContent {
	public BoxCmdHeader BoxCmdHeader;
	public String strcontent;//Box¶¯×÷
	public String strMessage;
	public void SetBoxContent(BoxCmdHeader BoxCmdHeader,String strContent) {
		strMessage = "BN" + BoxCmdHeader.rpt_code + BoxCmdHeader.bn_m0 + 
				BoxCmdHeader.bn_m1 + BoxCmdHeader.bn_m2 + strContent;
	}
	public String getBoxContent() {
		return this.strMessage;
	}
}
