
close all
clear all
datapath = 'Test1';
datapath = '���α�ǩ';
% datapath = '�߼�������';
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