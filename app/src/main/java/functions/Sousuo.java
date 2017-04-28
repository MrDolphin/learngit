package functions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.javacodegeeks.R;
import com.javacodegeeks.androidBluetoothExample.BluetoothChatService;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_single_choice;
import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;
import static functions.Protocol.readMessage;

public class Sousuo  extends Activity {
    byte[] data_return = new byte[13];//存放多个字节数组
    String data_return_string;

    static byte data_b = 0, data_c = 0, data_d = 0;
    static int data_a = 0;

    private static final String[] data1 = {"10kHz", "100kHz", "1000kHz"};
    private static final String[] data2 = {"Lora", "FSK"};
    private static final String[] data3 = {"125kHz", "250kHz", "500kHz"};
    private static final String[] data4 = {"4/5", "4/6", "4/7", "4/8", "4/9"};
    private static final String[] data5 = {"14dBm", "20dBm"};
    private static final String[] data6 = {"10s", "15s", "20s"};


    private static final int[] MF = {10000, 100000, 1000000};
    private static final Byte[] CR = {1, 2, 3, 4, 5};
    private static final Byte[] BW = {0, 1, 2};
    private static final Byte[] SP = {1, 2};

    private TextView mview, mview2,receive;
    private Spinner mspinner;
    private Spinner mspinner_mo;
    private Spinner mspinner_b_w;
    private Spinner mspinner_c_r;
    private Spinner mspinner_send;
    private Spinner mspinner_wait;


    private ArrayAdapter<String> madapter;
    private ArrayAdapter<String> madapter2;
    private ArrayAdapter<String> madapter3;
    private ArrayAdapter<String> madapter4;
    private ArrayAdapter<String> madapter5;
    private ArrayAdapter<String> madapter6;


    private Button button;
    private EditText mEtMsg;

    private List<String> messageList = new ArrayList<String>();
   // private int counter =0;
   private BluetoothChatService mChatService = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sousuo);

        //initDatas();
        init();
        initspinner(mspinner, madapter);
        initspinner(mspinner_mo, madapter2);
        initspinner(mspinner_b_w, madapter3);
        initspinner(mspinner_c_r, madapter4);
        initspinner(mspinner_send, madapter5);
        initspinner(mspinner_wait, madapter6);

      //  mChatService = new BluetoothChatService(this, mHandler2);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quanju.x = "aBCEFGHI\n";
                //   Data_Packet();

//                xuliehao++;
//                if(xuliehao >256)
//                    xuliehao = 0;

//                Intent intent = new Intent();
//                intent.putExtra("data_return", data_return_string);
//                setResult(RESULT_OK, intent);

                //BluetoothChatService.write(data_return);
                BluetoothChatService.mConnectedThread.write(data_return);
                receive.setText(readMessage);
               //BluetoothChat.sendMessage(data_return_string);

//                Message msg = new Message();
//                msg.obj = data_return_string + BluetoothActivity.BlueToothAddress;
//                msg.what = STATUS_CONNECT;
//                mHandler.sendMessage(msg);

//                sendMessageHandle(data_return_string);

                //  sendMessageHandle(data_return_string);

//                finish();
//                sendMessageHandle(Quanju.x);
            }
        });
//        mEtMsg = (EditText) findViewById(R.id.textView4);
//        mEtMsg.clearFocus();
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                String text = mEtMsg.getText().toString();
//                if (!TextUtils.isEmpty(text)) {
//                    // 发送信息
//                    sendMessageHandle(text);
//
//                    mEtMsg.setText("");
//                    mEtMsg.clearFocus();
//                    // 隐藏软键盘
//                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    manager.hideSoftInputFromWindow(mEtMsg.getWindowToken(), 0);
//                } else
//                    Toast.makeText(B_Sousuo.this, "发送内容不能为空！", Toast.LENGTH_SHORT).show();
//            }
//        });


    }
//
//    private final Handler mHandler2 = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case MESSAGE_WRITE:
//                    byte[] writeBuf = (byte[]) msg.obj;
//                    // construct a string from the buffer
//                    String writeMessage = new String(writeBuf);
//                    mAdapter.notifyDataSetChanged();
//                    messageList.add( writeMessage);
//                    break;
//                case MESSAGE_READ:
//                    byte[] readBuf = (byte[]) msg.obj;
//                    // construct a string from the valid bytes in the buffer
//                   // String readMessage = new String(readBuf, 0, msg.arg1);
//                    mAdapter.notifyDataSetChanged();
//
//                    receive.setText(readMessage);
//
//                    messageList.add(readMessage);
//                    break;
//
//            }
//        }
//    };

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {

            if (arg0 == mspinner) {
                data_a = MF[arg2];
                mview.setText("中心频率是：" + data_a);
            } else if (arg0 == mspinner_c_r) {
                data_b = CR[arg2];
            } else if (arg0 == mspinner_b_w) {
                data_c = BW[arg2];
            } else if (arg0 == mspinner_send) {
                data_d = SP[arg2];
            }
            Protocol protocol = new Protocol();
            data_return = protocol.Protocol_Sousuo(data_a, data_b, data_c, data_d);
            data_return_string = protocol.bytesToHexString(data_return);
            mview2.setText(data_return_string);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
            mview.setText("什么也没有选中");
        }
    }

    private void init() {

        receive = (TextView) findViewById(R.id.receive);
        mview = (TextView) findViewById(R.id.C_F_text);
        mview2 = (TextView) findViewById(R.id.textView);
        mspinner = (Spinner) findViewById(R.id.C_F_spinner);
        mspinner_mo = (Spinner) findViewById(R.id.Modulation_spinner);
        mspinner_b_w = (Spinner) findViewById(R.id.B_W_spinner);
        mspinner_c_r = (Spinner) findViewById(R.id.C_R_spinner);
        mspinner_send = (Spinner) findViewById(R.id.Send_spinner);
        mspinner_wait = (Spinner) findViewById(R.id.Wait_spinner);

        madapter = new ArrayAdapter<String>(this, simple_spinner_dropdown_item, data1);
        madapter.setDropDownViewResource(simple_spinner_item);
        madapter.setDropDownViewResource(simple_list_item_single_choice);

        madapter2 = new ArrayAdapter<String>(this, simple_spinner_dropdown_item, data2);
        madapter2.setDropDownViewResource(simple_spinner_item);
        madapter2.setDropDownViewResource(simple_list_item_single_choice);

        madapter3 = new ArrayAdapter<String>(this, simple_spinner_dropdown_item, data3);
        madapter3.setDropDownViewResource(simple_spinner_item);
        madapter3.setDropDownViewResource(simple_list_item_single_choice);

        madapter4 = new ArrayAdapter<String>(this, simple_spinner_dropdown_item, data4);
        madapter4.setDropDownViewResource(simple_spinner_item);
        madapter4.setDropDownViewResource(simple_list_item_single_choice);

        madapter5 = new ArrayAdapter<String>(this, simple_spinner_dropdown_item, data5);
        madapter5.setDropDownViewResource(simple_spinner_item);
        madapter5.setDropDownViewResource(simple_list_item_single_choice);

        madapter6 = new ArrayAdapter<String>(this, simple_spinner_dropdown_item, data6);
        madapter6.setDropDownViewResource(simple_spinner_item);
        madapter6.setDropDownViewResource(simple_list_item_single_choice);


//
//        initadapter(madapter, data1);
//        initadapter(madapter2, data2);

    }

    private void initspinner(Spinner spinner, ArrayAdapter<String> adapter) {
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinner.setVisibility(View.VISIBLE);
    }
}
//   private void initadapter(ArrayAdapter<String> adapter, String[] mdata){
//       adapter = new ArrayAdapter<String>(this,simple_spinner_dropdown_item,mdata);
//       adapter.setDropDownViewResource(simple_spinner_item);
//       adapter.setDropDownViewResource(simple_list_item_single_choice);
//   }
//    private Byte Data_Packet(){
//        data_return[0] = 0x04;
//        data_return[1] = 0x11;
//        data_return[2] = 0x06;
//        data_return[3] = 0x00;
//        data_return[4] = 0x0d;
//
//
//    }


//    // 发送数据
//    @Override
//    void sendMessageHandle(String msg) {
//        if (super.mSocket == null) {
//            Toast.makeText(this, "没有连接", Toast.LENGTH_SHORT).show();
//            return;
//        }
//            try {
//            OutputStream os = super.mSocket.getOutputStream();
//            os.write(msg.getBytes());
//
//            super.mDatas.add(new DeviceBean(msg, false));
//            super.mAdapter.notifyDataSetChanged();
//            super.mListView.setSelection(super.mDatas.size() - 1);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


// 开启服务器
//
//    protected class ServerThread extends Thread {
//        public void run() {
//            try {
//                // 创建一个蓝牙服务器 参数分别：服务器名称、UUID
//                mServerSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(PROTOCOL_SCHEME_RFCOMM,
//                        UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
//
//                Message msg = new Message();
//                msg.obj = "请稍候，正在等待客户端的连接...";
//                msg.what = 0x11;
//                mHandler.sendMessage(msg);
//
//				/* 接受客户端的连接请求 */
//                mSocket = mServerSocket.accept();
//
//                msg = new Message();
//                msg.obj = "客户端已经连接上！可以发送信息。";
//                msg.what = 0x11;
//                mHandler.sendMessage(msg);
//                // 启动接受数据
//                mReadThread = new ReadThread();
//                mReadThread.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    };
//
//    /**
//     * 信息处理
//     */
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            String info = (String) msg.obj;
//            switch (msg.what) {
//                case 0x11:
//                   // Toast.makeText(ChatActivity.super, info,Toast.LENGTH_SHORT ).show();
//                    break;
//            }
//            if (msg.what == 1) {
//                mDatas.add(new DeviceBean(info, true));
//                mAdapter.notifyDataSetChanged();
//                mListView.setSelection(mDatas.size() - 1);
//            }else {
//                mDatas.add(new DeviceBean(info, false));
//                mAdapter.notifyDataSetChanged();
//                mListView.setSelection(mDatas.size() - 1);
//            }
//        }
//
//    };
//
//}
