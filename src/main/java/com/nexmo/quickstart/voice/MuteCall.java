package com.nexmo.quickstart.voice;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.CallEvent;
import com.nexmo.client.voice.ModifyCallAction;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class MuteCall {
    public static void main(String... args) throws Exception {
        configureLogging();

        String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");

        NexmoClient client = NexmoClient.builder()
                .applicationId(NEXMO_APPLICATION_ID)
                .privateKeyPath(NEXMO_PRIVATE_KEY_PATH)
                .build();

        final String NEXMO_NUMBER = envVar("NEXMO_NUMBER");
        final String TO_NUMBER = envVar("TO_NUMBER");
        /*
        Establish a call for testing purposes.
         */
        CallEvent call = client.getVoiceClient().createCall(new Call(
                TO_NUMBER,
                NEXMO_NUMBER,
                "https://gist.githubusercontent.com/yallen011/e720c5d127791e0995a9359c195c1eaa/raw/882dfde8e26ebcd167628a576d244d8915cbaac4/long-tts.json"
        ));

        /*
        Give them time to answer.
         */
        Thread.sleep(10000);

        final String UUID = call.getUuid();
        client.getVoiceClient().modifyCall(UUID, ModifyCallAction.MUTE);
        Thread.sleep(3000);
        client.getVoiceClient().modifyCall(UUID, ModifyCallAction.UNMUTE);
    }
}
