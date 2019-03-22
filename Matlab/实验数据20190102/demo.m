clear all;
close all;
% path = 'С��';
path = 'С��';
addpath(path);
data = load('postion1_circle2.txt');
epc_col=1;
rssi_col =3;
time_col =4;
unique(data(:,epc_col))
index=data(:,epc_col)==2;
plot(data(index,rssi_col))
mm = data(index,:)
% ͷ10��
a = unique(data(1:10,rssi_col));
% β10��
b = unique(data(end-9:end,rssi_col));
% ȥͷ
begin =1;
for i =1:length(mm)
    if(max(min(abs(a-mm(i,3))))>0.5)
        begin = i;
        break;
    end
end
last = length(mm);
for i = length(mm):-1:1
    if(max(min(abs(a-mm(i,3))))>0.5)
        last = i;
        break;
    end
end
time_first = mm(begin,time_col);
time_last = mm(last,time_col);
mid = mean([time_first time_last])
index = data(:,time_col)<=mid+18000&data(:,time_col)>=mid-18000;
data = data(index,:);
% save 'postion1_circle1.txt' data -ascii
index = data(:,epc_col)==1;
figure
plot(data(index,rssi_col));