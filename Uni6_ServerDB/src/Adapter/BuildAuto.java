package Adapter;
/* Project_1_Unit_4
 * Name: Baiyang(Keen) Wang
 * andrew id: baiyangw
 */
import Scale.EditThread;
import Server.AutoServer;

public class BuildAuto extends proxyAutomobile 
					implements CreateAuto, UpdateAuto, FixAuto, EditThread,
							   AutoServer{
}
