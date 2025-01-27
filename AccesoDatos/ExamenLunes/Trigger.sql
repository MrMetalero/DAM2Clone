
CREATE OR REPLACE FUNCTION primerTrigger() 
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM Borrowing WHERE MemberID = NEW.MemberID AND ReturnDate IS NULL) >= 5 THEN
        RAISE EXCEPTION 'A member cannot borrow more than 5 books at a time';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER primerTrigger
BEFORE INSERT ON Borrowing
FOR EACH ROW EXECUTE FUNCTION primerTrigger();

