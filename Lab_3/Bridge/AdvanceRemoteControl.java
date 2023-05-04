package TMPS.Lab_3.Bridge;

public  class AdvanceRemoteControl extends RemoteControl {
    public AdvanceRemoteControl(Device device) {
        super(device);
    }
    public void setChannel(int number) {
        device.setChannel(number);
    }
}
