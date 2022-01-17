package birdrun.state

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class Test_KeyboardObserver extends Specification {
    Screen screen
    KeyboardObserver keyboardObserver
    KeyboardObserver mockKeyboardObserver

    def setup() {
        screen = Mock(Screen.class)
        keyboardObserver = new KeyboardObserver(screen)
        mockKeyboardObserver = Mock(KeyboardObserver.class)
    }

    def "Test listenRead"() {
        when:
        keyboardObserver.listenRead()

        then:
        1 * screen.readInput()
    }

    def "Test ListenPoll"() {
        when:
        keyboardObserver.listenPoll()

        then:
        1 * screen.pollInput()
    }

    def "Test interpretKey"() {
        when:
        KeyStroke key1 = null
        KeyStroke key2 = Stub(KeyStroke); key2.getKeyType() >> KeyType.EOF
        KeyStroke key3 = Stub(KeyStroke); key3.getKeyType() >> KeyType.ArrowUp
        KeyStroke key4 = Stub(KeyStroke); key4.getKeyType() >> KeyType.ArrowDown
        KeyStroke key5 = Stub(KeyStroke); key5.getKeyType() >> KeyType.ArrowRight
        KeyStroke key6 = Stub(KeyStroke); key6.getKeyType() >> KeyType.ArrowLeft
        KeyStroke key7 = Stub(KeyStroke); key7.getKeyType() >> KeyType.Enter
        KeyStroke key8 = Stub(KeyStroke); key8.getKeyType() >> KeyType.Tab
        KeyStroke key9 = Stub(KeyStroke); key9.getKeyType() >> KeyType.Character


        then:
        Command.COMMAND.NONE == keyboardObserver.interpertKey(key1)
        Command.COMMAND.QUIT == keyboardObserver.interpertKey(key2)
        Command.COMMAND.UP == keyboardObserver.interpertKey(key3)
        Command.COMMAND.DOWN == keyboardObserver.interpertKey(key4)
        Command.COMMAND.RIGHT == keyboardObserver.interpertKey(key5)
        Command.COMMAND.LEFT == keyboardObserver.interpertKey(key6)
        Command.COMMAND.SELECT == keyboardObserver.interpertKey(key7)
        Command.COMMAND.NONE == keyboardObserver.interpertKey(key8)
        keyboardObserver.interpertKey(key9) != null

    }

    def "Test interpertCharKey"() {
        when:
        KeyStroke key1 = Stub(KeyStroke); key1.getCharacter() >> "y"
        KeyStroke key2 = Stub(KeyStroke); key2.getCharacter() >> "Q"
        KeyStroke key3 = Stub(KeyStroke); key3.getCharacter() >> "P"
        KeyStroke key4 = Stub(KeyStroke); key4.getCharacter() >> "w"
        KeyStroke key5 = Stub(KeyStroke); key5.getCharacter() >> "A"
        KeyStroke key6 = Stub(KeyStroke); key6.getCharacter() >> "s"
        KeyStroke key7 = Stub(KeyStroke); key7.getCharacter() >> "d"

        then:
        Command.COMMAND.NONE == keyboardObserver.interpertCharKey(key1)
        Command.COMMAND.QUIT == keyboardObserver.interpertCharKey(key2)
        Command.COMMAND.PAUSE == keyboardObserver.interpertCharKey(key3)
        Command.COMMAND.UP == keyboardObserver.interpertCharKey(key4)
        Command.COMMAND.LEFT == keyboardObserver.interpertCharKey(key5)
        Command.COMMAND.DOWN == keyboardObserver.interpertCharKey(key6)
        Command.COMMAND.RIGHT == keyboardObserver.interpertCharKey(key7)

    }

}
