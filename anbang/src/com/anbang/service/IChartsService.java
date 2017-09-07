package com.anbang.service;

import java.util.Date;
import java.util.List;

import com.anbang.po.Charts;
import com.anbang.vo.ChartsVo;

public interface IChartsService extends ICommonService<Charts> {
		/**
		 * 获得charts表格数据，打包成json
		 * @param check_id
		 * @return
		 */
		

		List<ChartsVo> getSaftyChartsVo(String epid, String type, Date start, Date end);
		
		/**
		 * 返回企业和监管机构数据的时间横坐标，升序排序
		 * @param epid
		 * @return
		 */
		public List<String> getDoubleTimes(String epid,Date start,Date end);
		

		
		/**
		 * 写入chart表格最后的数据
		 * @param infoId
		 */
		public void saveChart(String infoId,String chartType,String epId);
		/**
		 * 返回对比数据chartsVo
		 * @param epid
		 * @return
		 */
		List<ChartsVo> getDoubleChartsVo(String epid, String chartType, Date start, Date end);
		/**
		 * 获得自查自报数据以及监督检查数据中的不符合项
		 * @param epid
		 * @return
		 */
		List<ChartsVo> getSelfChartsVo(String epid);
		
		List<Date> getSafetyCheckDate(String epid, Date Start, Date end,String chartType);

	

		
}
