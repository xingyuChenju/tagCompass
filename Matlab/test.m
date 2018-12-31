
close all
clear all
datapath = 'Test1';
datapath = '方形标签';
% datapath = '线极化天线';
% datapath = 'Data';
addpath(datapath);
ant_type = 's';
epc_col =1;
phase_col =2;
rssi_col =3;
% data = load([datapath '\0s.txt'])
data = load([datapath '\test3s.txt'])
figure
plot(data(:,rssi_col));
ylim([-50 -30])