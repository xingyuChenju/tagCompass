clear all;
close all;
path = 'С��';
path = '���';
addpath(path);
data = load('0.txt');
epc_col=1;
unique(data(:,epc_col))