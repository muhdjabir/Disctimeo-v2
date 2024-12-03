import { useState } from 'react'
import { Card, CardContent, CardHeader, CardTitle, CardFooter } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Loader2 } from 'lucide-react'

interface UserProfile {
    id: string
    username: string
    email: string
    startedPlaying: number
    position: string
    teams: string[]
    events: {
        past: string[]
        upcoming: string[]
    }
}

interface ProfileEditProps {
    profile: UserProfile
    onSave: (updatedProfile: UserProfile) => void
    onCancel: () => void
    isSaving: boolean
}

export function ProfileEdit({ profile, onSave, onCancel, isSaving }: ProfileEditProps) {
    const [editedProfile, setEditedProfile] = useState<UserProfile>(profile)

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target
        setEditedProfile(prev => ({ ...prev, [name]: value }))
    }

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault()
        onSave(editedProfile)
    }

    return (
        <form onSubmit={handleSubmit}>
            <Card>
                <CardHeader>
                    <CardTitle>Edit Profile</CardTitle>
                </CardHeader>
                <CardContent>
                    <div className="grid gap-4">
                        <div>
                            <Label htmlFor="username">Username</Label>
                            <Input
                                id="username"
                                name="username"
                                value={editedProfile.username}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div>
                            <Label htmlFor="email">Email</Label>
                            <Input
                                id="email"
                                name="email"
                                type="email"
                                value={editedProfile.email}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div>
                            <Label htmlFor="startedPlaying">Started Playing (Year)</Label>
                            <Input
                                id="startedPlaying"
                                name="startedPlaying"
                                type="number"
                                value={editedProfile.startedPlaying}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div>
                            <Label htmlFor="position">Position</Label>
                            <Input
                                id="position"
                                name="position"
                                value={editedProfile.position}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        {/* Note: Teams and Events editing are omitted for simplicity.
                 You may want to add more complex UI for editing these arrays. */}
                    </div>
                </CardContent>
                <CardFooter className="flex justify-between">
                    <Button type="button" variant="outline" onClick={onCancel}>Cancel</Button>
                    <Button type="submit" disabled={isSaving}>
                        {isSaving ? (
                            <>
                                <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                                Saving...
                            </>
                        ) : (
                            'Save Changes'
                        )}
                    </Button>
                </CardFooter>
            </Card>
        </form>
    )
}

