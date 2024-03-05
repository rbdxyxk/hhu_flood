USE slhdsms;
DROP TABLE IF EXISTS `cskgjz`,`dictionary`,`fb_zhan`,`jwd`,`newbao`,`pa`,`pa_end`,`st_addvcd_d`,`st_astrotd_f`,`st_dayev_r`,`st_ennmcd_d`,`st_eqpqav_r`,
`st_eqpqevs_r`,`st_errinf_r`,`st_estat_r`,`st_euntlang_d`,`st_ffrar_b`,`st_forecast_f`,`st_forecastc_f`,`st_frapar_b`,`st_fsdr_b`,`st_gate_f`,
`st_grw_f`,`st_ice_f`,`st_infsmry_r`,`st_instcd_b`,`st_instcd_e`,`st_instcd_old_b`,`st_myavsri_b`,`st_netstat_e`,`st_pddmyav_s`,`st_pdmmyav_b`,
`st_pdmmyav_s`,`st_pdmmysq_s`,`st_pmevs_r`,`st_pump_r`,`st_qliceinf_r`,`st_qticeinf_r`,`st_recvfile_e`,`st_recvinf_e`,`st_recvstat_e`,`st_reglat_f`,
`st_rfdr_f`,`st_river_f`,`st_rnfl_f`,`st_rsvr_r_0531`,`st_rsvrevs_r`,`st_rsvrmyav_b`,`st_rsvrmyav_s`,`st_rsvrstrl_b`,`st_rvav_r`,`st_rvdaymyav_s`,
`st_rvdmevsq_s`,`st_rvdmmyav_s`,`st_rvdmmysq_s`,`st_rvevs_r`,`st_rvfcch_b`,`st_rvsect_b`,`st_rvyevsq_s`,`st_sed_f`,`st_sed_r`,`st_sedfr_f`,`st_sedrf_f`,
`st_sedrf_r`,`st_senddo_e`,`st_sendfile_e`,`st_sendstat_e`,`st_sendto_e`,`st_sendwait_e`,`st_soil_f`,`st_soil_r_t`,`st_soilch_b`,`st_spec_r`,
`st_st58_b`,`st_stsmtask_b`,`st_syncset_e`,`st_syslog_e`,`st_tdfr_f`,`st_teleinf_r`,`st_temp_e`,`st_telesnd_r`,`st_tide_f`,`st_tide_r`,`st_tideav_r`,
`st_tideevs_r`,`st_tmp_r`,`st_tmpav_r`,`st_tmpevs_r`,`st_was_f`,`st_wasav_r`,`st_wasevs_r`,`st_wasrl_b`,`st_wdpstat_r`,`st_wdwv_r`,`st_wgrw_r`,`st_wgrwstat_r`,
`st_wspavsd_r`,`st_zqfrar_b`,`st_zqrlsyn_b`,`st58`,`stix`,`tp_hdjbxx`,`tp_hisshiyu`,`tp_histqxs`,`tp_jnxianyu`,`tp_shangqing`,`tp_skjbxx`,
`tp_yljbxx`,`ybgjzh`,`ybgjzh$`,`ybzhcsh_1`,`ybzhcsh_1$`,`ybzhcsh_2`,`ybzhcsh_2$`,`ybzhcsh_3`,`ybzhcsh_3$`,`ybzhcsh_4$`,`ybzhcsh_4`,`ybzhlb`,
`ybzhlb$`,`yn_zhan`;

#测试
#select * from `st_rsvr_r` as a join `st_stbprp_b` as b on a.`STCD`=b.stcd where a.`STCD`=41814288;
UPDATE  st_stbprp_b SET STNM='石梁河',rvnm='石梁河' WHERE STCD = '41814288';