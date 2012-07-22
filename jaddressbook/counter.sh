#!/bin/bash

a=`cat counter.dat`
let a=a+1
echo "Build count : $a";

echo $a > ./counter.dat
bdate=`date`
bplatform=`uname -s -r  -p  -o `
bversion=` cat version.dat`
echo "  
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
                private String buildcount=\"$a\";
		private String buildDate=\"$bdate\";
		private String buildPlatform=\"$bplatform\";
		private String buildVersion=\"$bversion\";

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
		
        }" > ./src/util/build.java;
