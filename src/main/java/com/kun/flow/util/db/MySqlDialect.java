/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */

package com.kun.flow.util.db;

/**
 * copy from hibernate(请勿改变，包括文件最开始的Copyright注释，尊重作者)
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年7月5日 下午3:45:03
 */
public class MySqlDialect {

	/**
	 * copy from hibernate
	 * 
	 * @author songkun
	 * @create 2014年7月5日 下午3:46:14
	 * @since
	 * @param sql
	 * @param hasOffset
	 * @return
	 */
	public static final String getLimitString(String sql, boolean hasOffset) {
		return new StringBuffer(sql.length() + 20).append(sql).append(hasOffset ? " limit ?, ?" : " limit ?")
				.toString();
	}
}
