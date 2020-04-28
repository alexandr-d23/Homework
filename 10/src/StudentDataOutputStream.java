import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StudentDataOutputStream extends OutputStream implements AutoCloseable{
    private DataOutputStream out;

    public StudentDataOutputStream(OutputStream out) {
        this.out = new DataOutputStream(out);
    }

    public void writeStudent(Student student) throws IOException {
        try {
            out.writeUTF(student.getName());
            out.writeBoolean(student.isGender());
            out.writeInt(student.getBirthYear());
            out.writeShort(student.getGroup());
        }
        catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    public void writeBoolean(boolean v) throws IOException {
        out.writeBoolean(v);
    }

    public void writeByte(int v) throws IOException {
        out.writeByte(v);
    }

    public void writeShort(int v) throws IOException {
        out.writeShort(v);
    }

    public void writeChar(int v) throws IOException {
        out.writeChar(v);
    }

    public void writeInt(int v) throws IOException {
        out.writeInt(v);
    }

    public void writeLong(long v) throws IOException {
        out.writeLong(v);
    }

    public void writeFloat(float v) throws IOException {
        out.writeFloat(v);
    }

    public void writeDouble(double v) throws IOException {
        out.writeDouble(v);
    }

    public void writeBytes(String s) throws IOException {
        out.writeBytes(s);
    }

    public void writeChars(String s) throws IOException {
        out.writeChars(s);
    }

    public void writeUTF(String str) throws IOException {
        out.writeUTF(str);
    }

    public int size() {
        return out.size();
    }

}
