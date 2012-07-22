  
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
	package util;
        public class build {
                private String buildcount="684";
		private String buildDate="Sat Feb 17 10:01:49 EET 2007";
		private String buildPlatform="Linux 2.6.18.2-34-default i686 GNU/Linux";
		private String buildVersion="0.5";

                public String getBuild() {
                return this.buildcount;
                }

		public String getBuildDate(){
		return this.buildDate;
		}

		public String getBuildPlatform() {
		return this.buildPlatform;
		}

		public String getBuildVersion() {
		return this.buildVersion;
		}
		
        }
